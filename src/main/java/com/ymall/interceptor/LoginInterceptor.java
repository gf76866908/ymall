package com.ymall.interceptor;

import com.ymall.annotation.AccessRequired;
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
            //没有声明需要权限,或者声明不验证权限
            if (accessRequired == null || !accessRequired.validate())
                return true;
            else {
                //在这里实现自己的权限验证逻辑
                User user = (User) httpServletRequest.getSession().getAttribute(Const.CURRENT_USER);
                if (user == null) {
                    throw new UnauthorizedException("未登录");
                } else {return true;}
            }
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
