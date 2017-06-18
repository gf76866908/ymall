package com.ymall.controller.admin;

import com.ymall.annotation.AdminReqired;
import com.ymall.common.Const;
import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.pojo.Product;
import com.ymall.service.ProductService;
import com.ymall.vo.PageModel;
import com.ymall.vo.ProductDetailVo;
import com.ymall.vo.ProductListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by zc on 2017/6/14.
 */
@RestController
@RequestMapping("/admin/product")
public class ProductMangerController {

    @Autowired
    private ProductService productService;

    //添加商品
    @AdminReqired
    @RequestMapping(value = "product", method = RequestMethod.POST)
    public ServerResponse addProduct(@Valid Product product) throws IllegalException {
        return productService. saveOrUpdateProduct(product);
    }

    //修改商品信息
    @AdminReqired
    @RequestMapping(value = "product/{productId}", method = RequestMethod.PUT)
    public ServerResponse updateProduct(@PathVariable Integer productId,@Valid Product product) throws IllegalException {
        product.setId(productId);
        return productService.saveOrUpdateProduct(product);
    }

    //删除商品信息
    @AdminReqired
    @RequestMapping(value = "product/{productId}", method = RequestMethod.DELETE)
    public ServerResponse deleteProduct(@PathVariable Integer productId) throws IllegalException {
        return productService.setSaleStatus(productId, Const.ProductStatusEnum.DELETE_SALE.getCode());
    }


    //修改商品状态
    @AdminReqired
    @RequestMapping(value = "sale_status/{productId}", method = RequestMethod.PUT)
    public ServerResponse setSaleStatus(@PathVariable Integer productId, @RequestParam Integer status) throws IllegalException {
        return productService.setSaleStatus(productId,status);
    }

    //获取所有商品详情
    @AdminReqired
    @RequestMapping(value = "product_detail/{productId}",method = RequestMethod.GET)
    public ServerResponse<ProductDetailVo> getProductDetail(@PathVariable Integer productId) throws IllegalException {
        return productService.getProductDetail(null,productId);
    }

    @AdminReqired
    @RequestMapping(value = "product_list",method = RequestMethod.GET)
    //获取所有商品列表(带分类与搜索)
    public ServerResponse<PageModel<ProductListVo>> getProductList(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer productId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(defaultValue = "id_asc") String orderBy,
            @Valid PageModel pageModel) throws IllegalException {

        int pageNum=pageModel.getPageNum();
        int limit=pageModel.getLimit();

        return productService.getProductList(
                null,
                keyword,
                productId,
                categoryId,
                orderBy,
                pageNum,
                limit);
    }
}
