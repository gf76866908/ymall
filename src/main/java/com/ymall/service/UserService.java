package com.ymall.service;

import com.ymall.common.ServerResponse;
import com.ymall.common.exception.UnauthorizedException;
import com.ymall.pojo.User;

/**
 * Created by zc on 2017/6/13.
 */
public interface UserService {
    ServerResponse<User> login  (String username,String password) throws UnauthorizedException;
}
