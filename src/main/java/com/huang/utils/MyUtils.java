package com.huang.utils;


import com.google.common.collect.Lists;
import com.huang.entity.Function;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by huang on 2019/1/27.
 */
public class MyUtils {
    /**
     * 获取集合第一个元素
     * @param list
     * @param <T>
     * @return 如果集合为空返回null 如果集合不为空 则返回集合的第一个元素
     */
    public static <T> T fetch(List<T> list){

       if(!ObjectUtils.isEmpty(list)){
           return list.get(0);
       }else
           return null;
    }

    /**
     * 将list转tree结构
     * @param functions
     * @return
     */
   public static List<Function> getTree(List<Function> functions){
        for(Function function:functions){
            if(0 == function.getParentId().intValue()){
                function.setChildren(findChildren(function.getFunctionId(),functions));
                return Lists.newArrayList(function);
            }
        }
        return functions;
   }

    /**
     * 通过递归的方式将不断查询子父结点，形成tree结构
     * @param pid
     * @param functions
     * @return
     */
   public static List<Function> findChildren(Integer pid,List<Function> functions){
       List<Function> children=new ArrayList<>();
       for(Function function:functions){
           if(pid.intValue() == function.getParentId().intValue()){
               function.setChildren(findChildren(function.getFunctionId(),functions));
               children.add(function);
           }
       }
       return children;
   }
}
