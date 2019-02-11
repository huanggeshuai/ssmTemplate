package com.huang.aop.annoation;

import java.lang.annotation.*;

/**
 * Created by huang on 2019/1/28.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidDo {
//不想写很多的重复代码 就利用注解解决
}
