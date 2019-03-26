package com.huang.exception;

import com.google.common.collect.Lists;
import com.huang.base.BaseController;
import com.huang.exception.myexception.DevException;
import com.huang.exception.myexception.RoleFunctionException;
import com.huang.exception.myexception.TimeCompaeException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Created by huang on 2019/01/28.
 * 在这里 处理全局异常
 */
@ControllerAdvice
public class HandleException extends BaseController<HandleException> {

    @ResponseBody
    @ExceptionHandler(UnknownAccountException.class)
    Object handUserNotExitException(UnknownAccountException e){
        return failure(e.getMessage());
    }
    
    @ResponseBody
    @ExceptionHandler(IncorrectCredentialsException.class)
    Object handPasswordException(IncorrectCredentialsException e){
        return failure(e.getMessage());
    }

    @ExceptionHandler(UnauthenticatedException.class)
    ModelAndView UnauthenticatedException(UnauthenticatedException e){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("sys/error/500");
        modelAndView.addObject("info","您尚未登入，请重新登入获取认证");
        return modelAndView;
    }

    @ResponseBody
    @ExceptionHandler(ExcessiveAttemptsException.class)
    Object handleExcessiveAttemptsException(ExcessiveAttemptsException e){
        return failure(e.getMessage());
    }



    @ResponseBody
    @ExceptionHandler(RoleFunctionException.class)
    Object roleFunctionException(RoleFunctionException e){
        return failure(e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    ModelAndView AuthorizationException(AuthorizationException e){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("sys/error/500");
        modelAndView.addObject("info","您没有权限访问该页面,请联系管理员获取该操作权限");
        return modelAndView;
    }

    /**
     * 处理检验参数异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    Object handleParamException(ConstraintViolationException e){
        List<String> list= Lists.newLinkedList();
        for(ConstraintViolation constraintViolation:e.getConstraintViolations()){
            list.add(constraintViolation.getMessageTemplate());
        }
        return failure(list.toString());
    }

    /**
     * 处理两个时间比较的异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(TimeCompaeException.class)
    Object handleTimeCompareException(TimeCompaeException e){

        return failure(e.toString());
    }
    @ExceptionHandler(DevException.class)
    ModelAndView DevException(DevException e){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("sys/error/500");
        modelAndView.addObject("info","不能修改权限信息");
        return modelAndView;
    }
}
