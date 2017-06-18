package com.ymall.service;

import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.vo.CartVo;

import java.util.List;

/**
 * Created by zc on 2017/6/17.
 */
public interface CartService {
    ServerResponse<CartVo> addProductToCart(Integer userId, Integer productId, Integer count) throws IllegalException;

    ServerResponse<CartVo> updateCart(Integer userId, Integer productId, Integer count) throws IllegalException;

    ServerResponse<CartVo> deleteProduct(Integer userId, Integer productId) throws IllegalException;

    ServerResponse<CartVo> getCartList(Integer userId) throws IllegalException;

    ServerResponse<CartVo> selectOrUnSelect(Integer id, Integer productId, Boolean checked) throws IllegalException;
}
