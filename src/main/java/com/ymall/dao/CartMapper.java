package com.ymall.dao;

import com.ymall.pojo.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart selectCartByUserIdProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    List<Cart> selectCartByUserId(Integer userId);

    int selectCartProductCheckedCount(@Param("userId") Integer userId);

    int deleteByUserIdProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    int deleteByUserIdChecked(Integer userId);

    int checkedOrUncheckedProduct(@Param("userId") Integer userId,
                                  @Param("productId") Integer productId,
                                  @Param("checked") Boolean checked);

    int selectCartProductCount(@Param("userId") Integer userId);
}