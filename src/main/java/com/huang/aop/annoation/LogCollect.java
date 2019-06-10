package com.huang.aop.annoation;

import java.lang.annotation.*;

/**
 * @Classname LogCollect
 * @Description 日志收集注解
 * @Date 2019/4/14 15:53
 * @Created by huang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogCollect {
    String msg() default "" ;  //日志信息说明

}
