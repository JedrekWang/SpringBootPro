package com.jedrek.interceptor;


import com.jedrek.annotation.Licence;
import com.jedrek.utils.LicenceCache;
import com.jedrek.utils.ValidationLicence;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 授权权限拦截类
 * Created by wangjie22438 on 2017/8/29.
 */
@Component
@Aspect
public class LicenceHandlerInterceptor {

    private static final Logger log = Logger.getLogger(LicenceHandlerInterceptor.class);

    @Autowired
    private LicenceCache licenceCache;

    @Autowired
    private ValidationLicence validationLicence;

    @Pointcut("@within(com.jedrek.annotation.Licence)")
    public void pointCutForClass(){};

    @Pointcut("@annotation(com.jedrek.annotation.Licence)")
    public void pointCutForMethod(){};

    @Around("pointCutForClass() || pointCutForMethod()")
    public void aroundInvoke(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
        //获取拦截目标方法的Method对象
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();

        //得到注解对象，获取注解的属性
        Licence licence = targetMethod.getAnnotation(Licence.class);
        String moduleCode = licence.code();
        String licenceCode = licence.licenceCode();
        String errorTip = licence.errorTip();
        //核查是否有相关授权权限
        boolean valid = validationLicence.checkRightByFunctionCode(moduleCode,licenceCode);
        licenceCache.put(moduleCode,licenceCode);
        if(valid) {
            try {
                log.error("匹配成功,可以执行");
                // 匹配成功调用的方法正常执行
                proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }else {
            log.error("无权访问");
        }
    }

}
