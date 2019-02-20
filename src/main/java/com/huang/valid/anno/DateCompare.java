package com.huang.valid.anno;

import com.huang.valid.config.DateCompareConfig;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

//时间校验注解 专门校验开始时间和结束时间的
@Constraint(validatedBy = DateCompareConfig.class) //验证的方法类
@Target({ METHOD, FIELD,CONSTRUCTOR, ANNOTATION_TYPE,PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface DateCompare {
    String message() default "{time is early}";

    int begin() ; //必填 就是开始时间 就是该参数请求的位置比如在第一个位置 就填1 第二个位置就填2

    int end() ;  //必填 结束时间 就是改参数请求的位置


    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
