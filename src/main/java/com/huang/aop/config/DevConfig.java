package com.huang.aop.config;

import com.huang.base.Base;
import com.huang.exception.myexception.DevException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @auther huang
 * @create 2019-03-20 12:24
 * 如果是开发环境，允许修改权限信息，否则不允许修改权限信息
 */
@Component
@Aspect
public class DevConfig extends Base<DevConfig> {

    /**
     * 声明式aop
     * 匹配FunctionController的add，edit和del方法
     */
    @Pointcut("execution(* com.huang.controller.FunctionController.add*(..)) || " +
              "execution(* com.huang.controller.FunctionController.edit*(..))|| " +
              "execution(* com.huang.controller.FunctionController.del*(..))")
    public void pointcut(){

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        if(!sysInfoBean.isDev()){
            throw new DevException("不能修改权限信息");
        }
        return point.proceed();
    }
}
