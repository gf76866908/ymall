package com.ymall.controller.portal;

import com.ymall.annotation.AccessRequired;
import com.ymall.common.Const;
import com.ymall.common.ServerResponse;
import com.ymall.common.exception.BaseException;
import com.ymall.common.exception.IllegalException;
import com.ymall.pojo.Address;
import com.ymall.pojo.User;
import com.ymall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by zc on 2017/7/4.
 */

@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @AccessRequired
    @RequestMapping(value = "address", method = RequestMethod.POST)
    public ServerResponse add(HttpSession session, Address address) throws IllegalException {
        User user_login = (User) session.getAttribute(Const.CURRENT_USER);
        return addressService.add(user_login.getId(), address);
    }

    @AccessRequired
    @RequestMapping(value = "address/{addressId}",method = RequestMethod.DELETE)
    public ServerResponse delete(HttpSession session,@PathVariable Integer addressId) throws BaseException {
        User user_login = (User) session.getAttribute(Const.CURRENT_USER);
        return  addressService.delete(user_login.getId(),addressId);
    }
}
