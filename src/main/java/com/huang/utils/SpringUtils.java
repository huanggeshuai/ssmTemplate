package com.huang.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description 在非spring环境下获取spring的bean
 * @auther huang
 * @create 2019-03-25 9:49
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    //spring上下文环境
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.context=applicationContext;
    }

    /**
     * 根据别名获取bean
     * @param name bean注册名
     * @return
     */
    public static <T> T getBean(String name){
       return (T) context.getBean(name);
    }

    /**
     * 获取类型为requiredType的对象 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
     *
     * @param name
     *            bean注册名
     * @param requiredType
     *            返回对象类型
     * @return Object 返回requiredType类型对象
     * @throws BeansException
     */
    public static Object getBean(String name, Class<Object> requiredType) throws BeansException {
        return context.getBean(name, requiredType);
    }

    /**
     * 根据class获取spring容器的bean
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class requiredType){
        return (T) context.getBean(requiredType);
    }
}
