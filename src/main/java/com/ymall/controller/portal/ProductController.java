package com.ymall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.ymall.common.Const;
import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.service.CategoryService;
import com.ymall.service.ProductService;
import com.ymall.vo.PageModel;
import com.ymall.vo.ProductDetailVo;
import com.ymall.vo.ProductListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by zc on 2017/6/15.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //获取在售商品详情
    @RequestMapping(value = "product_detail/{productId}",method = RequestMethod.GET)
    public ServerResponse<ProductDetailVo> getProductDetail(@PathVariable Integer productId) throws IllegalException {
        return productService.getProductDetail(Const.ProductStatusEnum.ON_SALE.getCode(),productId);
    }

    @RequestMapping(value = "product_list",method = RequestMethod.GET)
    //获取在售商品列表(带分类与搜索)
    public ServerResponse<PageModel<ProductListVo>> getProductList(
            String keyword,
            Integer productId,
            Integer categoryId,
            @RequestParam(defaultValue = "id_asc") String orderBy,
            @Valid PageModel pageModel) throws IllegalException {

        int pageNum=pageModel.getPageNum();
        int limit=pageModel.getLimit();

        return productService.getProductList(
                Const.ProductStatusEnum.ON_SALE.getCode(),
                keyword,
                productId,
                categoryId,
                orderBy,
                pageNum,
                limit);
    }

}
