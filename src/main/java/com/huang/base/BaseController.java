package com.huang.base;

import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Created by huang on 2019/1/27.
 */
public class BaseController<T> extends Base {
  public Logger logger= LogManager.getLogger(this.getClass());


  /**
   * 前端bootstrap valid框架要求的json格式
   * @return
   */
  public Object vaildTrue(){
    Map map=Maps.newHashMap();
    map.put("valid",true);
    return map;
  }

  public Object vaildFailure(){
    Map map=Maps.newHashMap();
    map.put("valid",false);
    return map;
  }

}
