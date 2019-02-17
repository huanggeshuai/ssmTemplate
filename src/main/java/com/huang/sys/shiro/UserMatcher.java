package com.huang.sys.shiro;

import com.huang.sys.info.SysInfoBean;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class UserMatcher extends HashedCredentialsMatcher {

    public Logger logger= LogManager.getLogger(this.getClass());


    @Autowired
    private SysInfoBean sysInfoBean;

    private Cache<String, AtomicInteger> loginCache;//登入缓存

    @Setter
    private Integer maxFailureCount;//最大失败登入次数

    @Setter
    private Integer initLoginCount;//初始化登入次数

    public UserMatcher(CacheManager cacheManager, String cacheName) {
        this.loginCache = cacheManager.getCache(cacheName);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //获取用户名
        String userName = (String) token.getPrincipal();
        //获取失败次数
        AtomicInteger count = loginCache.get(userName);
        //如果为空
        //则初始化init 0
        if(ObjectUtils.isEmpty(count)){
            count = new AtomicInteger(initLoginCount);
        }
        //将对象放入缓存
        loginCache.put(userName,new AtomicInteger(count.incrementAndGet()));
         //如果失败次数大于5次则锁定用户
        if(count.get()>=maxFailureCount){
            logger.error("{}:{}",userName,sysInfoBean.getLockInfo());

            throw new ExcessiveAttemptsException(sysInfoBean.getLockInfo());
        }

        boolean match= super.doCredentialsMatch(token, info);

        if(match){
            loginCache.remove(userName);
        }else {
            //提醒用户剩余次数
            logger.error("{}:{}",userName,String.format(sysInfoBean.getLockCount(),
                    userName,
                    (maxFailureCount-count.get())));
            throw new
                    IncorrectCredentialsException(
                            String.format(sysInfoBean.getLockCount(),
                                    userName,
                                    (maxFailureCount-count.get())));
        }

        return match;
    }
}
