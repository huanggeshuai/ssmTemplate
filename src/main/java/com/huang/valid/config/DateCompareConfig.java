package com.huang.valid.config;

import com.huang.exception.myexception.TimeCompaeException;
import com.huang.valid.anno.DateCompare;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.util.Date;

@SupportedValidationTarget(ValidationTarget.PARAMETERS) //表示验证的参数
public class DateCompareConfig implements ConstraintValidator<DateCompare,Object[]> {

   private Integer beginLocation;  //定义开始时间的位置

   private Integer endLogincation;//定义结束时间的位置

    @Override
    public boolean isValid(Object[] objects, ConstraintValidatorContext constraintValidatorContext) {
        if(beginLocation.intValue() == endLogincation.intValue() ){
            throw new TimeCompaeException("time is same,unable to compare"); //时间相同无法比较
        }
        if((beginLocation.intValue() - 1 <0) ||(endLogincation.intValue() - 1 <0) ){
            throw new TimeCompaeException("time location incorrectly,unable to compare"); //时间相同无法比较
        }

        if((beginLocation.intValue() >objects.length) ||(endLogincation.intValue() > objects.length) ){
            throw new TimeCompaeException("time location is too long,unable to compare"); //如果时间的下标比数组还要长
        }

        Date beginDate = null;  //先初始化参数

        Date endDate = null;  //先初始化参数

        if(objects[beginLocation.intValue()-1] instanceof Date){
            beginDate=(Date) objects[beginLocation.intValue()-1];
        }
        if(objects[endLogincation.intValue()-1] instanceof Date){
            endDate=(Date) objects[endLogincation.intValue()-1];
        }
        //如果时间参数都没空 则该注解无效
        if(ObjectUtils.isEmpty(beginDate)&&ObjectUtils.isEmpty(endDate)){
            return true;
        }

        //如果时间参数有一个为空 则直接返回false
        if(ObjectUtils.isEmpty(beginDate)||ObjectUtils.isEmpty(endDate)){
            return false;
        }

        return beginDate.before(endDate);
    }

    //初始化 就是赋初值
    @Override
    public void initialize(DateCompare constraintAnnotation) {
        beginLocation=constraintAnnotation.begin();
        endLogincation=constraintAnnotation.end();
    }
}
