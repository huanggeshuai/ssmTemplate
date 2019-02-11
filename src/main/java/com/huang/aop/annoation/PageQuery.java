package com.huang.aop.annoation;

import java.lang.annotation.*;

/**
 * Created by huang on 2019/2/5.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageQuery {
    int pageNo() default 1;   //默认显示第一页

    int pageSize() default 10;//默认一页10条数据
}
