package com.ymall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ymall.common.Const;
import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.dao.CategoryMapper;
import com.ymall.dao.ProductMapper;
import com.ymall.pojo.Category;
import com.ymall.pojo.Product;
import com.ymall.service.CategoryService;
import com.ymall.service.ProductService;
import com.ymall.util.DateTimeUtil;
import com.ymall.util.PropertiesUtil;
import com.ymall.vo.PageModel;
import com.ymall.vo.ProductDetailVo;
import com.ymall.vo.ProductListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2017/6/14.
 */
@Service("produceService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;

    //新增或更新商品
    public ServerResponse saveOrUpdateProduct(Product product) throws IllegalException {
        if (product != null) {
            if (StringUtils.isNotBlank(product.getSubImages())) {
                String[] subImageArray = product.getSubImages().split(",");
                if (subImageArray.length > 0) {
                    product.setMainImage(subImageArray[0]);
                }
            }
            if (product.getId() != null) {
                int rowCount = productMapper.updateByPrimaryKeySelective(product);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("更新商品成功");
                }
                throw new IllegalException("更新商品失败");
            } else {
                if (product.getStatus() == null) {
                    //默认上线
                    product.setStatus(Const.ProductStatusEnum.ON_SALE.getCode());
                    //初始化销量
                    product.setSales(0);
                }

                int rowCount = productMapper.insertSelective(product);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("新增商品成功");
                }
                throw new IllegalException("新增商品失败");
            }
        }
        throw new IllegalException("商品参数不正确");
    }

    /*
    * 修改商品状态.
    * 1-在售
    * 2-下架
    * 3-删除
    * */
    public ServerResponse setSaleStatus(Integer productId, Integer status) throws IllegalException {

        if (status == null || !Const.ProductStatusEnum.contains(status)) {
            throw new IllegalException("status参数错误,不在范围内");
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("修改商品销售状态成功");
        }
        throw new IllegalException("修改商品销售状态失败亦或商品不存在");
    }


    public ServerResponse<ProductDetailVo> getProductDetail(Integer productId,Integer ProductStatus) throws IllegalException {

        Product product = productMapper.selectByPrimaryKeyAndStatus(ProductStatus,productId);
        if (product == null) {
            throw new IllegalException("商品不存在或已经下架");
        }
              //ToDo 浏览次数
        ProductDetailVo productDetailVo = assembleProductDetailVo(product);
        return ServerResponse.createBySuccess(productDetailVo);
    }

    public ServerResponse<PageModel<ProductListVo>> getProductList(
            Integer productStatus,
            String keyword,
            Integer productId,
            Integer categoryId,
            String orderBy,
            int pageNum,
            int pageSize) throws IllegalException {

        //添加%
        if (StringUtils.isNotBlank(keyword)) {
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }
        //获取所有子分类id
        List<Integer> categoryIds = categoryService.selectAllChildrenId(categoryId);

        //startPage--start
        //填充自己的sql查询逻辑
        //pageHelper-收尾
        PageHelper.startPage(pageNum, pageSize);

        //通过PageHelper排序处理
        if(StringUtils.isNotBlank(orderBy)){
            if(Const.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)){
                String[] orderByArray = orderBy.split("_");
                PageHelper.orderBy(orderByArray[0]+" "+orderByArray[1]);
            }
        }


        List<Product> productList = productMapper.selectList(
                productStatus,
                StringUtils.isBlank(keyword)?null:keyword,
                productId,
                categoryIds);

        List<ProductListVo> productListVoList = Lists.newArrayList();
        for (Product productItem : productList) {
            ProductListVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }
        PageInfo pageResult = new PageInfo<>(productList);
        pageResult.setList(productListVoList);
        PageModel<ProductListVo> pageModel = PageModel.convertToPageModel(pageResult);
        return ServerResponse.createBySuccess(pageModel);
    }


    //组装ProductListVo
    private ProductListVo assembleProductListVo(Product product) {
        ProductListVo productListVo = new ProductListVo();
        productListVo.setId(product.getId());
        productListVo.setName(product.getName());
        productListVo.setCategoryId(product.getCategoryId());
        productListVo.setMainImage(PropertiesUtil.getProperty("cos.server.http.prefix") + product.getMainImage());
        productListVo.setPrice(product.getPrice());
        productListVo.setSales(product.getSales());
        productListVo.setSubtitle(product.getSubtitle());
        productListVo.setStatus(product.getStatus());
        return productListVo;
    }


    //组装ProductDetailVo
    private ProductDetailVo assembleProductDetailVo(Product product) {
        ProductDetailVo productDetailVo = new ProductDetailVo();
        productDetailVo.setId(product.getId());
        productDetailVo.setSubtitle(product.getSubtitle());
        productDetailVo.setPrice(product.getPrice());

        productDetailVo.setCategoryId(product.getCategoryId());
        productDetailVo.setDetail(product.getDetail());
        productDetailVo.setName(product.getName());
        productDetailVo.setStatus(product.getStatus());
        productDetailVo.setStock(product.getStock());
        productDetailVo.setSales(product.getSales());


        productDetailVo.setMainImage(PropertiesUtil.getProperty("cos.server.http.prefix") + product.getMainImage());

        //将图像列表解开
        List<String> url_list = new ArrayList<String>();
        String subImages = product.getSubImages();
        if(subImages!=null){
            String[] split = subImages.split(",");
            for (String url : split) {
                url_list.add(PropertiesUtil.getProperty("cos.server.http.prefix") + url);
            }
        }
        productDetailVo.setSubImages(url_list);

        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if (category == null) {
            productDetailVo.setParentCategoryId(0);//没有品类就把它当做根节点
        } else {
            productDetailVo.setParentCategoryId(category.getParentId());
        }

        productDetailVo.setCreateTime(DateTimeUtil.dateToStr(product.getCreateTime()));
        productDetailVo.setUpdateTime(DateTimeUtil.dateToStr(product.getUpdateTime()));
        return productDetailVo;
    }







}
