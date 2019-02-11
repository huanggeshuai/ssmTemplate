package com.huang.sys.shiro.filter;

import com.huang.sys.shiro.UserRealm;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by huang on 2019/2/11.
 */
public class SysLogoutFilter extends LogoutFilter {
    @Autowired
    private UserRealm userRealm;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
      //  userRealm.clearAuthen();
        Cache<Object, AuthenticationInfo> cache = userRealm.getAuthenticationCache();
        cache.keys();
        userRealm.getAuthenticationCacheName();

        return super.preHandle(request, response);
    }
}
