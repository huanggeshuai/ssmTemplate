package com.huang.exception.myexception;

import com.huang.base.BaseException;

/**
 * @Description
 * @auther huang
 * @create 2019-03-20 12:55
 * 当出现这个异常说明非开发情况下修改了权限信息
 */
public class DevException extends BaseException {

    public DevException(String message) {
        super(message);
    }
}
