package com.ymall.controller.admin;

import com.ymall.common.Const;
import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.common.exception.UnauthorizedException;
import com.ymall.pojo.User;
import com.ymall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by zc on 2017/6/13.
 */
@RestController
@RequestMapping("/admin/user")
public class UserMangerController {
    @Autowired
    private UserService userService;

    //后台登录
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ServerResponse<User> login(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String password, HttpSession session) throws UnauthorizedException, IllegalException {
        ServerResponse<User> response = userService.login(username, password);
        // 向session中加入当前用户信息
        if (response.isSuccess()) {
            if(response.getData().getRole()==Const.Role.ROLE_ADMIN){
                session.setAttribute(Const.CURRENT_USER, response.getData());
                return response;
            }else {
                throw new UnauthorizedException("不是管理员,无法登录");
            }
        }
        throw new IllegalException("登录失败");
    }
}
