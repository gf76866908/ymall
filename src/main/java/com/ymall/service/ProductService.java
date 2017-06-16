package com.ymall.service;

import com.github.pagehelper.PageInfo;
import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.pojo.Product;
import com.ymall.vo.PageModel;
import com.ymall.vo.ProductDetailVo;
import com.ymall.vo.ProductListVo;

/**
 * Created by zc on 2017/6/14.
 */
public interface ProductService {
    ServerResponse saveOrUpdateProduct(Product product) throws IllegalException;

    ServerResponse setSaleStatus(Integer productId,Integer status) throws IllegalException;

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId,Integer ProductStatus) throws IllegalException;

    ServerResponse<PageModel<ProductListVo>> getProductList(
            Integer productStatus,
            String keyword,
            Integer productId,
            Integer categoryId,
            String crderBy,
            int pageNum,
            int pageSize) throws IllegalException;
}
