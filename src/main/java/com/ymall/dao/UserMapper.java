package com.ymall.dao;

import com.ymall.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    int checkEmail(String email);

    int checkEmailByUserId(@Param(value="email")String email,@Param(value="userId")Integer userId);
}