package com.huang.aop.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huang.aop.annoation.ValidDo;
import com.huang.base.Base;
import com.huang.base.BaseController;
import org.apache.commons.collections4.MapUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by huang on 2019/1/28.
 */
@Aspect
@Component
public class ValidDoConfig extends Base {
    @Around(value = " @annotation(validDo)")
    public Object around(ProceedingJoinPoint point, ValidDo validDo) throws Throwable {

        Object[] argsarr = point.getArgs();      //获取方法入参数组
        BindingResult result=null;
        for(int i=0;i<argsarr.length;i++){
            if(argsarr[i] instanceof BindingResult){
                result=(BindingResult) argsarr[i];
                break;
            }
        }

        if(!ObjectUtils.isEmpty(result)){
            if(result.hasErrors()){
                List<FieldError> fieldErrors=result.getFieldErrors();
                List<String> errors= Lists.newArrayList();
                for(FieldError fieldError:fieldErrors){
                     errors.add(fieldError.getDefaultMessage());
                 }
                 return failure(errors.toString());
            }
        }
       return point.proceed();
    }
}
