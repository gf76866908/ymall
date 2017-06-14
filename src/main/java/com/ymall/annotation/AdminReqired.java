package com.ymall.annotation;

import java.lang.annotation.*;

/**
 * Created by zc on 2017/6/13.
 *  需要管理员权限注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminReqired {
    boolean validate() default true;
}
