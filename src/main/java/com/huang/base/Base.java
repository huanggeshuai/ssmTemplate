package com.huang.base;

import com.google.common.collect.Maps;
import com.huang.sys.info.SysInfoBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by huang on 2019/1/27.
 */
public class Base<T> {
    @Autowired
    protected SysInfoBean sysInfoBean;

    private final boolean success=true;

    private final boolean failure=false;


    private Map map;


    public Map success(String msg){
        map= Maps.newHashMap();
        map.put("success",success);
        map.put("msg",msg);
        return map;
    }

    public Map failure(String msg){
        map= Maps.newHashMap();
        map.put("success",failure);
        map.put("msg",msg);
        return map;
    }
}
