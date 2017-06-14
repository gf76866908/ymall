package com.ymall.service.impl;

import com.ymall.common.Const;
import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.dao.CategoryMapper;
import com.ymall.dao.ProductMapper;
import com.ymall.pojo.Product;
import com.ymall.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zc on 2017/6/14.
 */
@Service("produceService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    public ServerResponse saveOrUpdateProduct(Product product) throws IllegalException {
        if(product != null)
        {
            if(StringUtils.isNotBlank(product.getSubImages())){
                String[] subImageArray = product.getSubImages().split(",");
                if(subImageArray.length > 0){
                    product.setMainImage(subImageArray[0]);
                }
            }
            if(product.getId() != null){
                int rowCount = productMapper.updateByPrimaryKeySelective(product);
                if(rowCount > 0){
                    return ServerResponse.createBySuccess("更新产品成功");
                }
                throw new IllegalException("更新产品失败");
            }else{
                if(product.getStatus()==null){
                    //默认上线
                    product.setStatus(Const.ProductStatusEnum.ON_SALE.getCode());
                }

                int rowCount = productMapper.insert(product);
                if(rowCount > 0){
                    return ServerResponse.createBySuccess("新增产品成功");
                }
                throw new IllegalException("新增产品失败");
            }
        }
        throw new IllegalException("产品参数不正确");
    }


    public ServerResponse deleteProduct(Integer productId) throws IllegalException {
        int rowCount = productMapper.deleteByPrimaryKey(productId);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("删除产品成功");
        }
        throw new IllegalException("删除产品失败");
    }

    /*
    * 商品状态.
    * 1-在售
    * 2-下架
    * 3-删除
    * */
    public ServerResponse setSaleStatus(Integer productId,Integer status) throws IllegalException {
        if(productId == null || status == null){
            throw new IllegalException("参数错误");
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("修改产品销售状态成功");
        }
        throw new IllegalException("修改产品销售状态失败");
    }

}
