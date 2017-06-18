package com.ymall.controller.portal;

import com.google.common.base.Splitter;
import com.ymall.annotation.AccessRequired;
import com.ymall.common.Const;
import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.pojo.User;
import com.ymall.service.CartService;
import com.ymall.vo.CartVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zc on 2017/6/17.
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    //添加商品到购物车
    @AccessRequired
    @RequestMapping(value = "cart_item", method = RequestMethod.POST)
    public ServerResponse<CartVo> addCart(HttpSession httpSession,
                                          @RequestParam Integer productId,
                                          @RequestParam Integer count) throws IllegalException {
        User user_login = (User) httpSession.getAttribute(Const.CURRENT_USER);
        return cartService.addProductToCart(user_login.getId(), productId, count);
    }

    //修改商品到购物车中商品数量
    @AccessRequired
    @RequestMapping(value = "cart_item", method = RequestMethod.PUT)
    public ServerResponse<CartVo> updateCart(HttpSession httpSession,
                                             @RequestParam Integer productId,
                                             @RequestParam Integer count) throws IllegalException {
        User user_login = (User) httpSession.getAttribute(Const.CURRENT_USER);
        return cartService.updateCart(user_login.getId(), productId, count);
    }

    //从购物车中删除某个商品
    @AccessRequired
    @RequestMapping(value = "cart_item/{productId}", method = RequestMethod.DELETE)
    public ServerResponse<CartVo> deleteCart(HttpSession httpSession,
                                             @PathVariable Integer productId) throws IllegalException {
        User user_login = (User) httpSession.getAttribute(Const.CURRENT_USER);
        return cartService.deleteProduct(user_login.getId(), productId);
    }

    //从购物车中删除所有选择商品
    @AccessRequired
    @RequestMapping(value = "cart_item_checked", method = RequestMethod.DELETE)
    public ServerResponse<CartVo> deleteCart(HttpSession httpSession) throws IllegalException {
        User user_login = (User) httpSession.getAttribute(Const.CURRENT_USER);
        return cartService.deleteProduct(user_login.getId(), null);
    }

    //获取购物车列表
    @AccessRequired
    @RequestMapping(value = "cart_list", method = RequestMethod.GET)
    public ServerResponse<CartVo> getCartList(HttpSession httpSession) throws IllegalException {
        User user_login = (User) httpSession.getAttribute(Const.CURRENT_USER);
        return cartService.getCartList(user_login.getId());
    }


    //全选或全反选
    @AccessRequired
    @RequestMapping(value = "check_all", method = RequestMethod.POST)
    public ServerResponse<CartVo> selectOrUnSelect(HttpSession httpSession,
                                                   @RequestParam Boolean checked) throws IllegalException {
        User user_login = (User) httpSession.getAttribute(Const.CURRENT_USER);
        return cartService.selectOrUnSelect(user_login.getId(), null, checked);
    }


    //单选或单反选
    @AccessRequired
    @RequestMapping(value = "check_single", method = RequestMethod.POST)
    public ServerResponse<CartVo> selectOrUnSelect(HttpSession httpSession,
                                                   @RequestParam Integer productId,
                                                   @RequestParam Boolean checked) throws IllegalException {
        User user_login = (User) httpSession.getAttribute(Const.CURRENT_USER);
        return cartService.selectOrUnSelect(user_login.getId(), productId, checked);
    }

}
