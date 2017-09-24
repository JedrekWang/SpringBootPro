package com.jedrek.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * licence注解,该注解的方法或类下面所有的方法都需要验证权限
 * Created by wangjie22438 on 2017/8/29.
 */
//目标为方法和类
@Target({ElementType.TYPE, ElementType.METHOD})
//运行时调用
@Retention(RetentionPolicy.RUNTIME)
public @interface Licence {
    String code() default"";
    String licenceCode() default "";
    String errorTip() default "";
}
