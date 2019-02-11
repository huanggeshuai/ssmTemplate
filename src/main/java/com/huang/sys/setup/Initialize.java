package com.huang.sys.setup;

import com.huang.base.Base;
import com.huang.entity.User;
import com.huang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by huang on 2019/1/27.
 *
 */
@Component
public class Initialize extends Base<Initialize> {
    @Autowired
    private UserService userService;

    /**
     * 当系统加载好就会执行init()方法
     */
    @PostConstruct
    void init(){
        initUser();
    }
    void initUser(){
        if (userService.count()==0){
            /**
             * 说明user表中没有用户
             * 那么系统就会给他加一个用户
             * 用户名:admin
             *  密码:admin
             */
            User user= User.builder().username(sysInfoBean.getDefaltUserName()).password(sysInfoBean.getDefaltUserPassword()).build();

            userService.addUser(user);
        }
    }
}
