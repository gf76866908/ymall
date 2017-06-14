package com.ymall.interceptor;

import com.ymall.annotation.AccessRequired;
import com.ymall.annotation.AdminReqired;
import com.ymall.common.Const;
import com.ymall.common.exception.UnauthorizedException;
import com.ymall.pojo.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zc on 2017/6/13.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            AccessRequired accessRequired = ((HandlerMethod) handler).getMethodAnnotation(AccessRequired.class);
            AdminReqired adminReqired = ((HandlerMethod) handler).getMethodAnnotation(AdminReqired.class);
            //管理员验证
            if (adminReqired != null && adminReqired.validate()) {
                User user = (User) httpServletRequest.getSession().getAttribute(Const.CURRENT_USER);
                if (user == null) {
                    throw new UnauthorizedException("未登录");
                }
                if(user.getRole()!=Const.Role.ROLE_ADMIN){
                    throw new UnauthorizedException("你不是管理员无权访问");
                }
                return true;
            }


            //普通登录验证
            if (accessRequired != null && accessRequired.validate()) {
                User user = (User) httpServletRequest.getSession().getAttribute(Const.CURRENT_USER);
                if (user == null) {
                    throw new UnauthorizedException("未登录");
                }
                return true;
            }

            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
