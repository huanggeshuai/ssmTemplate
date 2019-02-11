package com.huang.utils;

import com.huang.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

/**
 * Created by huang on 2019/1/27.
 */
public class ShiroUtils {
    /**
     *
     * @param user 未加密的用户
     * @param encryptName 加密算法名称 支持md5，sha-1,sha-256,sha-384,sha-512
     * @param hashIterations 加密次数
     * @return
     */
    public static User encrype(User user,String encryptName,int hashIterations) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        if(StringUtils.isEmpty(user.getPassword())){
            return user;
        }
        String orginPassword = user.getPassword();
        ByteSource byteSource = ByteSource.Util.bytes(salt);
        SimpleHash hash = new SimpleHash(encryptName, orginPassword,
                byteSource, hashIterations);
        user.setSalt(salt);
        user.setPassword(hash.toHex());
        return user;
    }


    /**
     *
     * @param password 未加密的密码
     * @param encryptName 加密算法名称 支持md5，sha-1,sha-256,sha-384,sha-512
     * @param hashIterations 加密次数
     * @return
     */
    public static String encrype(String password,String salt,String encryptName,int hashIterations) {

        ByteSource byteSource = ByteSource.Util.bytes(salt);
        SimpleHash hash = new SimpleHash(encryptName, password,
                byteSource, hashIterations);
        return hash.toHex();
    }

    /**
     * 从shiro获取用户
     * @return
     */
    public static User getUser(){
        Object user = SecurityUtils.getSubject().getPrincipal();
        if(!ObjectUtils.isEmpty(user)){
            if (user instanceof User) {
                return (User)user;
            }
        }
        return null;
    }

    /**
     * 字符串链接
     * @param strs
     * @return
     */
    public static String getPassword(String... strs){
        return StringUtils.join(strs);
    }

}
