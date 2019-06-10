package com.huang.utils;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * @ClassName DataBaseEncrypt
 * @Description 数据库密码加密
 * @auther huang
 * @create 2019-06-10 11:11
 */
public class DataBaseEncrypt {

    public static void main(String[] args) throws Exception {
        String password = "123456";

        String keyPair[] = ConfigTools.genKeyPair(512);

        //私钥
        String privateKey = keyPair[0];

        //公钥
        String publicKey = keyPair[1];

        System.out.println("私钥:"+ privateKey);

        System.out.println("公钥:"+ publicKey);

        System.out.println("原密码:"+ password);

        //使用私钥来加密密码
        password = ConfigTools.encrypt(privateKey, password);

        System.out.println("加密之后密码:"+ password);


        String decryptPassword = ConfigTools.decrypt(publicKey,password);

        System.out.println("解密之后密码:"+ decryptPassword);

    }

}
