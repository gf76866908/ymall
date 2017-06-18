package com.ymall.vo;

import java.math.BigDecimal;
import java.util.List;


public class CartVo {

    private List<CartProductVo> cartItemList;
    private BigDecimal cartTotalPrice;// 总价
    private Integer checkedCount;//购物车中选中商品种类的数量
    private Integer productTotal; //购物车中选中商品总件数


    public BigDecimal getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(BigDecimal cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public Integer getCheckedCount() {
        return checkedCount;
    }

    public void setCheckedCount(Integer checkedCount) {
        this.checkedCount = checkedCount;
    }

    public List<CartProductVo> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartProductVo> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public Integer getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(Integer productTotal) {
        this.productTotal = productTotal;
    }
}
