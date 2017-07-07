package com.ymall.service.impl;

import com.ymall.common.ServerResponse;
import com.ymall.common.exception.BaseException;
import com.ymall.common.exception.IllegalException;
import com.ymall.common.exception.NotFoundException;
import com.ymall.common.exception.UnauthorizedException;
import com.ymall.dao.AddressMapper;
import com.ymall.pojo.Address;
import com.ymall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zc on 2017/7/4.
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    public ServerResponse add(Integer userId, Address address) throws IllegalException {
        address.setUserId(userId);
        int count = addressMapper.insertSelective(address);
        if (count>0) {
            Map<String, Integer> map=new HashMap<String, Integer>();
            map.put("id",address.getId());
            return ServerResponse.createBySuccess("创建地址成功",map);
        }else {
            throw new IllegalException("创建收货地址失败");
        }
    }

    public ServerResponse delete(Integer userId,Integer addressId) throws BaseException {
        Address address = addressMapper.selectByPrimaryKey(addressId);
        if(address!=null){
            if(address.getUserId().equals(userId)){
                int count = addressMapper.deleteByPrimaryKey(addressId);
                if (count>0){
                    return ServerResponse.createBySuccessMessage("删除成功");
                }else {
                    throw new IllegalException("删除失败");
                }
            }else {
                throw new UnauthorizedException("无权删除");
            }
        }else {
            throw new NotFoundException("地址不存在");
        }
    }

}
