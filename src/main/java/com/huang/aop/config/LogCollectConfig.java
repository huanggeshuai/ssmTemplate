package com.huang.aop.config;

import com.huang.aop.annoation.LogCollect;
import com.huang.base.Base;
import com.huang.entity.Log;
import com.huang.entity.User;
import com.huang.service.LogService;
import com.huang.utils.RequestUtils;
import com.huang.utils.ShiroUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Classname LogCollectConfig
 * @Description 对注解@LogCollect的实现
 * @Date 2019/4/14 15:54
 * @Created by huang
 */
@Aspect
@Component
public class LogCollectConfig extends Base<LogCollectConfig> implements Runnable {

    //自动注入线程池
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private LogService logService;

    private Log log;

    /**
     * 定义切入点
     * @param logCollect
     */
    @Pointcut("@annotation(logCollect)")
    public void point(LogCollect logCollect){

    }

    @Around(value = "point(logCollect)")
    public Object around(ProceedingJoinPoint joinPoint, LogCollect logCollect) throws Throwable {
        //初始化log
        log = Log.builder().build();
        //获取操作信息
        String msg = logCollect.msg();
        //获取注解的方法名称
        String methodNam = joinPoint.getSignature().getName();
        //获取当前的用户
        User user = ShiroUtils.getUser();
        if(!ObjectUtils.isEmpty(user)){
            //如果请求的用户存在
            log.setUserid(ShiroUtils.getUser().getUserid());
        }

        //放入ip
        log.setIp(RequestUtils.getIpAddr());
        log.setMethodName(methodNam);
        log.setMsg(msg);

        log.setOperateTime(new Date());
        Object processObj = null;
        try{
            processObj = joinPoint.proceed();
        }catch (Throwable throwable){
            log.setException(throwable.getMessage());
            //抛出异常，是为了不影响其他程序运行，否则全局处理异常的类就是摆设
            throw throwable;
        }finally {
            //开启线程池 处理日志
            threadPoolExecutor.execute(this);
        }
        return processObj;

    }

    @Override
    public void run() {
        logService.add(log);
    }
}
