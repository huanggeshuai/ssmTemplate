package com.huang.aop.config;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.huang.aop.annoation.PageQuery;
import com.huang.base.Base;
import com.huang.sys.info.SysInfoBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by huang on 2019/2/5.
 *
 * 分页注解
 */
@Aspect
@Component
public class PageQueryConfig extends Base {

    @Autowired
    private SysInfoBean sysInfoBean;

    @Around(value = "@annotation(query)")
    public Object around(ProceedingJoinPoint point, PageQuery query) throws Throwable{
        Integer pageNo=query.pageNo();
        Integer pageSize=query.pageSize();
        Object[] paramArr=point.getArgs();   //获取方法入参
        if(paramArr[0] instanceof Integer){
            pageNo=(Integer) paramArr[0];
        }
        if(paramArr[1] instanceof Integer){
            pageSize=(Integer) paramArr[1];
        }
        pageNo = pageNo == null?sysInfoBean.getPageNo():pageNo;
        pageSize = pageSize == null?sysInfoBean.getPageRow():pageSize;
        PageHelper.startPage(pageNo, pageSize);
        Object list=null;
        try{
            list=point.proceed();
            PageInfo pageInfo=null;
            if(list instanceof Page){   //如果返回的是list集合
                pageInfo=new PageInfo((Page) list);
                Map<String,Object> map= Maps.newHashMap();
                /**
                 * 返回符合bootstrap table格式的json数据
                 */
                map.put("total",pageInfo.getTotal());
                map.put("rows",pageInfo.getList());
                return map;
            }
            return list;
        }finally {
            if(!ObjectUtils.isEmpty(PageHelper.getLocalPage())){
                PageHelper.clearPage();   //清空线程变量
            }
        }
    }
}
