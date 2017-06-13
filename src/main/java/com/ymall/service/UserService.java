package com.ymall.service;

import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.common.exception.UnauthorizedException;
import com.ymall.pojo.User;

/**
 * Created by zc on 2017/6/13.
 */
public interface UserService {
    ServerResponse<User> login  (String username,String password) throws UnauthorizedException;

    ServerResponse<String> register(User user) throws IllegalException;

    ServerResponse<String> checkValid(String str, String type) throws IllegalException;

    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    ServerResponse<User> updateUserInfo(User user) throws IllegalException;
}
