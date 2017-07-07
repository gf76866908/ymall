package com.ymall.service;

import com.ymall.common.ServerResponse;
import com.ymall.common.exception.BaseException;
import com.ymall.common.exception.IllegalException;
import com.ymall.pojo.Address;
import com.ymall.pojo.User;

/**
 * Created by zc on 2017/7/4.
 */
public interface AddressService {
    ServerResponse add(Integer userId, Address address) throws IllegalException;

    ServerResponse delete(Integer userId,Integer addressId) throws BaseException;
}
