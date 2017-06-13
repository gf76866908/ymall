package com.ymall.annotation;

import java.lang.annotation.*;

/**
 * Created by zc on 2017/6/13.
 *  自动判断登录注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessRequired {
    boolean validate() default true;
}
