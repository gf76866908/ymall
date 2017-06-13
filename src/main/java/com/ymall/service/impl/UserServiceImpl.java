package com.ymall.service.impl;

import com.ymall.common.ServerResponse;
import com.ymall.common.exception.UnauthorizedException;
import com.ymall.dao.UserMapper;
import com.ymall.pojo.User;
import com.ymall.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zc on 2017/6/13.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username,String password) throws UnauthorizedException {

        //ToDo MD5加密
        User user_login = userMapper.selectLogin(username,password);
        if(user_login==null){
            throw new UnauthorizedException("用户不存在或密码错误");
        }
        //密码置空
        user_login.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",user_login);
    }
}
