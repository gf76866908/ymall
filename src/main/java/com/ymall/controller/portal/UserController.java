package com.ymall.controller.portal;

import com.ymall.annotation.AccessRequired;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //用户登录
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ServerResponse<User> login(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String password, HttpSession session) throws UnauthorizedException {

        ServerResponse<User> response = userService.login(username, password);

        // 向session中加入当前用户信息
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }

        return response;
    }

    //注销
    @AccessRequired
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess("注销成功");
    }

    //注册
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServerResponse<String> register(User user) throws IllegalException {
        if(user.getUsername()==null&&user.getPassword()==null){
            throw new IllegalException("注册信息不完整");
        }
        return userService.register(user);
    }

    //检查用户名或email
    @RequestMapping(value = "check_valid", method = RequestMethod.GET)
    public ServerResponse<String> checkValid(@RequestParam(required = true) String value, @RequestParam(required = true) String type) throws IllegalException {
        return userService.checkValid(value, type);
    }

    //获取用户信息
    @AccessRequired
    @RequestMapping(value = "my_info", method = RequestMethod.GET)
    public ServerResponse<User> getUserInfo(HttpSession session) throws IllegalException, UnauthorizedException {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess(user);
    }

    // 重置密码/忘记密码(未登录可以用)
    //ToDo 先放着
    @AccessRequired
    @RequestMapping(value = "reset_password", method = RequestMethod.POST)
    public ServerResponse<String> forgetRestPassword(
            String username,
            String passwordNew,
            String forgetToken){
        return userService.forgetResetPassword(username,passwordNew,forgetToken);
    }

    //修改用户信息
    @AccessRequired
    @RequestMapping(value = "my_info", method = RequestMethod.PUT)
    public ServerResponse<User> updateUserInfo(HttpSession session,User user) throws IllegalException {
        User user_login = (User) session.getAttribute(Const.CURRENT_USER);
        user.setId(user_login.getId());
        user.setUsername(user_login.getUsername());
        ServerResponse<User> userServerResponse = userService.updateUserInfo(user);
        if(userServerResponse.isSuccess()){
            userServerResponse.getData().setUsername(user_login.getUsername());
            session.setAttribute(Const.CURRENT_USER,userServerResponse.getData());
        }
        return userServerResponse;
    }
}
