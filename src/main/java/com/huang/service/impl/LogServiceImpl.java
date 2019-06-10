package com.huang.service.impl;

import com.huang.base.BaseService;
import com.huang.entity.Log;
import com.huang.mapper.LogMapper;
import com.huang.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname LogServiceImpl
 * @Description TODO
 * @Date 2019/4/14 17:32
 * @Created by huang
 */
@Service
public class LogServiceImpl extends BaseService<LogServiceImpl> implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public void add(Log log) {
        logMapper.insertSelective(log);
    }
}
