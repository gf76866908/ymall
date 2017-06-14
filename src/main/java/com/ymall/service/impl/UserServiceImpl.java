package com.ymall.service.impl;

import com.ymall.common.Const;
import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.common.exception.UnauthorizedException;
import com.ymall.dao.UserMapper;
import com.ymall.pojo.User;
import com.ymall.service.UserService;
import com.ymall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zc on 2017/6/13.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) throws UnauthorizedException {

        String md5Password = MD5Util.MD5EncodeUtf8(password);

        User user_login = userMapper.selectLogin(username, md5Password);
        if (user_login == null) {
            throw new UnauthorizedException("用户不存在或密码错误");
        }
        //密码置空
        user_login.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user_login);
    }

    @Override
    public ServerResponse<String> register(User user) throws IllegalException {
        ServerResponse<String> validResponse = this.checkValid(user.getUsername(),Const.USERNAME);
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        validResponse = this.checkValid(user.getEmail(),Const.EMAIL);
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        //设置角色
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5 加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int count = userMapper.insert(user);
        if (count <= 0) {
            throw new IllegalException("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) throws IllegalException {
        if (Const.USERNAME.equals(type)) {
            int count = userMapper.checkUsername(str);
            if (count > 0) {
                throw new IllegalException("用户名已存在");
            }else {
                return ServerResponse.createBySuccessMessage("用户名可以使用");
            }
        }
        else if(Const.EMAIL.equals(type)){
            int count = userMapper.checkEmail(str);
            if (count > 0) {
                throw new IllegalException("邮箱已存在");
            }else {
                return ServerResponse.createBySuccessMessage("邮箱可以使用");
            }
        }
        else {
            throw new IllegalException("参数错误");
        }
    }

    @Override
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
        return null;
    }

    @Override
    public ServerResponse<User> updateUserInfo(User user) throws IllegalException {
        //username 不能更新
        // email 不能重复
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(), user.getId());
        if(resultCount>0){
            throw new IllegalException("email已存在");
        }
        int update_count=userMapper.updateByPrimaryKeySelective(user);
        if(update_count>0){
            User response_user = userMapper.selectByPrimaryKey(user.getId());
            //密码置空
            response_user.setPassword(StringUtils.EMPTY);
            return ServerResponse.createBySuccess("更新个人信息成功",response_user);
        }
        throw new IllegalException("更新个人信息失败");
    }


}
