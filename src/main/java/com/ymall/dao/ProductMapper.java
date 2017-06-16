package com.ymall.dao;

import com.ymall.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Product selectByPrimaryKeyAndStatus(@Param("productStatus")Integer productStatus, @Param("productId") Integer productId);

    List<Product> selectList(
            @Param("productStatus")Integer productStatus,
            @Param("productName")String productName,
            @Param("productId") Integer productId,
            @Param("categoryIdList")List<Integer> categoryIdList
    );

}