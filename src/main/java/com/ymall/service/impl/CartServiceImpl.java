package com.ymall.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.ymall.common.Const;
import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.dao.CartMapper;
import com.ymall.dao.ProductMapper;
import com.ymall.pojo.Cart;
import com.ymall.pojo.Product;
import com.ymall.service.CartService;
import com.ymall.util.BigDecimalUtil;
import com.ymall.util.PropertiesUtil;
import com.ymall.vo.CartProductVo;
import com.ymall.vo.CartVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zc on 2017/6/17.
 */
@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;


    private static String imageHost = PropertiesUtil.getProperty("cos.server.http.prefix");

    //添加新产品
    public ServerResponse<CartVo> addProductToCart(Integer userId, Integer productId, Integer new_count) throws IllegalException {
        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        //商品不在购物车里
        if (cart == null) {
            Cart cart_new = new Cart();
            cart_new.setUserId(userId);
            cart_new.setProductId(productId);
            cart_new.setChecked(true);//新加的商品默认选中
            //检查库存
            if (checkStock(productId, new_count)) {
                cart_new.setQuantity(new_count);
                cartMapper.insert(cart_new);
            }
        } else {//商品已经存在 数量相加 更新商品记录
            //检查库存
            int buyCount = cart.getQuantity() + new_count;
            if (checkStock(productId, buyCount)) {
                cart.setQuantity(buyCount);
                cartMapper.updateByPrimaryKey(cart);
            }
        }
        CartVo cartVo = getCartVo(userId);
        return ServerResponse.createBySuccess("向购物车添加商品成功", cartVo);
    }

    //修改购物车商品数量
    public ServerResponse<CartVo> updateCart(Integer userId, Integer productId, Integer count) throws IllegalException {
        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        //商品不在购物车里
        if (cart != null) {
            if (checkStock(productId, count)) {
                cart.setQuantity(count);
                cartMapper.updateByPrimaryKey(cart);
                CartVo cartVo = getCartVo(userId);
                return ServerResponse.createBySuccess("修改商品数量成功", cartVo);
            }
        }
        throw new IllegalException("你的购物车无此商品");
    }

    //从购物车中删除某个商品或删除所有选择商品
    public ServerResponse<CartVo> deleteProduct(Integer userId, Integer productId) throws IllegalException {
        int i = 0;
        if (productId != null) {
            i = cartMapper.deleteByUserIdProductId(userId, productId);
        } else {
            i = cartMapper.deleteByUserIdChecked(userId);
        }
        if (i > 0) {
            CartVo cartVo = getCartVo(userId);
            return ServerResponse.createBySuccess("从购物车移除商品成功", cartVo);
        }
        throw new IllegalException("移除失败或未选择要移除的商品");
    }

    //获取购物车列表
    public ServerResponse<CartVo> getCartList(Integer userId) throws IllegalException {
        CartVo cartVo = getCartVo(userId);
        return ServerResponse.createBySuccess(cartVo);
    }

    //选或反选
    public ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, Boolean checked) throws IllegalException {
        int i = cartMapper.checkedOrUncheckedProduct(userId, productId, checked);
        if (i > 0) {
            CartVo cartVo = getCartVo(userId);
            return ServerResponse.createBySuccess("修改选择状态成功", cartVo);
        }
        throw new IllegalException("修改选择状态失败");
    }

    //判断购买数量是否大于库存或小于1
    private Boolean checkStock(Integer productId, Integer buyCount) throws IllegalException {
        Product product = productMapper.selectByPrimaryKeyAndStatus(Const.ProductStatusEnum.ON_SALE.getCode(),productId);

        if (product != null) {
            if (product.getStock() >= buyCount) {
                if (1 <= buyCount) {
                    return true;
                }
                throw new IllegalException("商品数量不能小于1");
            } else {
                throw new IllegalException("您所填加的商品数量超过库存!");
            }
        }
        throw new IllegalException("该商品不存在或已经下架");
    }

    //组装CartVo
    private CartVo getCartVo(Integer userId) {
        CartVo cartVo = new CartVo();//初始化购物车VO

        List<Cart> cartList = cartMapper.selectCartByUserId(userId);//获得该用户下的所有购物车记录

        List<CartProductVo> cartItemList = Lists.newArrayList();//初始化cartProductVoList购物车列表

        BigDecimal cartTotalPrice = new BigDecimal("0");//初始化购物车总价

        if (CollectionUtils.isNotEmpty(cartList)) {
            for (Cart cart : cartList) {
                CartProductVo cartProductVo = assembleCartProductVo(cart);
                if (cart.getChecked()) {
                    //如果已经勾选,增加到整个的购物车总价中
                    cartTotalPrice = BigDecimalUtil.add(
                            cartTotalPrice.doubleValue(),
                            cartProductVo.getProductTotalPrice().doubleValue());
                }
                cartItemList.add(cartProductVo);
            }
        }
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartItemList(cartItemList);

        //查询购物车中被选中的数量
        cartVo.setCheckedCount(cartMapper.selectCartProductCheckedCount(userId));

        //查询购物车中商品总数目
        cartVo.setProductTotal(cartMapper.selectCartProductCount(userId));

        return cartVo;
    }

    //组装CartProductVo
    private CartProductVo assembleCartProductVo(Cart cart) {
        CartProductVo cartProductVo = new CartProductVo();

        cartProductVo.setId(cart.getId());
        cartProductVo.setUserId(cart.getUserId());
        cartProductVo.setProductId(cart.getProductId());
        cartProductVo.setQuantity(cart.getQuantity());
        cartProductVo.setProductChecked(cart.getChecked());

        //填充商品信息
        Product product = productMapper.selectByPrimaryKey(cart.getProductId());
        if (product != null) {
            cartProductVo.setProductMainImage(imageHost + product.getMainImage());
            cartProductVo.setProductName(product.getName());
            cartProductVo.setProductSubtitle(product.getSubtitle());
            cartProductVo.setProductStatus(product.getStatus());
            cartProductVo.setProductPrice(product.getPrice());
            cartProductVo.setProductStock(product.getStock());
        }

        //计算并总价
        cartProductVo.setProductTotalPrice(BigDecimalUtil.mul(
                product.getPrice().doubleValue(),
                cartProductVo.getQuantity()));

        return cartProductVo;
    }

}
