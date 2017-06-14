package com.ymall.controller;

import com.ymall.common.ServerResponse;
import com.ymall.common.exception.BaseException;
import com.ymall.common.exception.IllegalException;
import com.ymall.common.exception.NotFoundException;
import com.ymall.common.exception.UnauthorizedException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

/**
 * Created by zc on 2017/6/13.
 */

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    //405 (请求方法不对)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public Object handServletIllegalException(HttpRequestMethodNotSupportedException e) {
        return ServerResponse.createByErrorCodeMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
    }

    //参数错误 400(bean 校验)
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handServletIllegalExceptionBean(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = "参数错误:\n";

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + "\n";
        }

        return ServerResponse.createByErrorCodeMessage(HttpStatus.BAD_REQUEST.value(), errorMesssage);
    }

    //参数错误 400
    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handServletIllegalException(Exception e) {
        return ServerResponse.createByErrorCodeMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    //参数错误 400 (手动)
    @ExceptionHandler(IllegalException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handIllegalException(IllegalException e) {
        return e.getErrorObject();
    }

    //未授权 401
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Object handUnauthorizedException(UnauthorizedException e) {
        return e.getErrorObject();
    }


    //未找到资源 404
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Object handNotFoundException(NotFoundException e) {
        return e.getErrorObject();
    }


    //默认异常 500(默认)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Object defaultErrorHandler(Exception e) {
        System.out.println(e);
        return ServerResponse.createByErrorMessage(e.getMessage());
    }

}
