package com.huang.exception;

import com.huang.base.BaseController;
import com.huang.exception.myexception.RoleFunctionException;
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

}
