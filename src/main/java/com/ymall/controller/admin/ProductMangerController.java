package com.ymall.controller.admin;

import com.ymall.annotation.AdminReqired;
import com.ymall.common.Const;
import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.pojo.Product;
import com.ymall.service.ProductService;
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
//    @Autowired
//    private FileService fileService;

    @AdminReqired
    @RequestMapping(value = "product", method = RequestMethod.POST)
    public ServerResponse addProduct(@Valid Product product) throws IllegalException {
        return productService. saveOrUpdateProduct(product);
    }

    @AdminReqired
    @RequestMapping(value = "product/{productId}", method = RequestMethod.PUT)
    public ServerResponse updateProduct(@PathVariable Integer productId,@Valid Product product) throws IllegalException {
        product.setId(productId);
        return productService.saveOrUpdateProduct(product);
    }

    @AdminReqired
    @RequestMapping(value = "product/{productId}", method = RequestMethod.DELETE)
    public ServerResponse deleteProduct(@PathVariable Integer productId) throws IllegalException {
        return productService.setSaleStatus(productId, Const.ProductStatusEnum.DELETE_SALE.getCode());
    }

    /*
    * 修改商品状态
    * */
    @AdminReqired
    @RequestMapping(value = "sale_status/{productId}", method = RequestMethod.PUT)
    public ServerResponse setSaleStatus(@PathVariable Integer productId, @RequestParam Integer status) throws IllegalException {
        return productService.setSaleStatus(productId,status);
    }
}
