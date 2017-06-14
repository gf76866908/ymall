package com.ymall.service;

import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.pojo.Product;

/**
 * Created by zc on 2017/6/14.
 */
public interface ProductService {
    ServerResponse saveOrUpdateProduct(Product product) throws IllegalException;

    ServerResponse setSaleStatus(Integer productId,Integer status) throws IllegalException;
}
