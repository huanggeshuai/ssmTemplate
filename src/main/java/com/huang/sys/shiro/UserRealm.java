package com.huang.sys.shiro;

import com.huang.entity.User;
import com.huang.service.RoleService;
import com.huang.service.UserService;
import com.huang.sys.info.SysInfoBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

/*
 * Created by huang on 2018/11/4.
 */


public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Autowired
    private SysInfoBean sysInfoBean;

    @Autowired
    private RoleService roleService;
    @Override
    //shiro 鉴权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user=(User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String role=roleService.findById(user.getRoleId()).getRoleCode();
        simpleAuthorizationInfo.addRole(role);
        simpleAuthorizationInfo.addStringPermissions(roleService.userFunctions(user.getRoleId()));
        return simpleAuthorizationInfo;
    }

    @Override
    //shiro认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken) token;
        String userName=usernamePasswordToken.getUsername();
        usernamePasswordToken.getPrincipal();
        User user= userService.findUserByName(userName);
         if(ObjectUtils.isEmpty(user)){
             throw new UnknownAccountException(String.format(sysInfoBean.getUnknowName(),userName));
         }
        String password=user.getPassword();
        String salt=user.getSalt();
        ByteSource byteSource= ByteSource.Util.bytes(salt);
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user,password,byteSource,getName());
        return info;
    }

    public void clearAuthen(){
        Subject subject=SecurityUtils.getSubject();
        super.clearCachedAuthenticationInfo(subject.getPrincipals());
    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        User user=(User) principals.getPrimaryPrincipal();
        if (!isEmpty(principals)) {
            Cache<Object, AuthenticationInfo> cache = this.getAuthenticationCache();
            //cache instance will be non-null if caching is enabled:
            if (cache != null) {
                Object key = user.getUsername();
                cache.remove(key);
            }
        }
        super.clearCachedAuthenticationInfo(principals);
    }

    private static boolean isEmpty(PrincipalCollection pc) {
        return pc == null || pc.isEmpty();
    }

}
