package com.jedrek.interceptor;

import com.jedrek.annotation.Licence;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by wangjie22438 on 2017/9/1.
 */
@Component
@Aspect
public class SimpleInterceptor {

    private Logger  log = Logger.getLogger(SimpleInterceptor.class);

    @Pointcut("@annotation(com.jedrek.annotation.Licence)")
    public void pointCut1() {}

    //@Around("pointCut1()")
    public void revokeBefore(ProceedingJoinPoint joinPoint) throws Throwable {

        //获取拦截目标方法的Method对象
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();

        //得到注解对象，获取注解的属性
        Licence licence = targetMethod.getAnnotation(Licence.class);
        String moduleCode = licence.code();
        String licenceCode = licence.licenceCode();
        String errorTip = licence.errorTip();
        boolean ans;
        if(ans = licenceCode.equals("password1")) {
            log.error(ans);
            joinPoint.proceed();
            log.error("say it again "+ans);
        } else {
            log.error("没有权限");
            return;
        }
    }
}
