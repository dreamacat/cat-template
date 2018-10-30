package com.cat.component;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author wangxiaoqiang
 * @since 2018/10/09
 **/
@Aspect
@Component
@Slf4j
public class MyAspect {

    @Before(value = "@annotation(com.cat.annotations.AopTestAnno) && args(code)")
    public void beforeMethod(JoinPoint joinPoint, String code) throws Throwable {
        System.out.println("beforeMethod，方法名称为：" + joinPoint.getSignature().getDeclaringTypeName() + ".." + joinPoint.getSignature().getName());
        System.out.println("beforeMethod，参数为：" + code);
        System.out.println("beforeMethod，目标对象为：" +(joinPoint.getTarget()));
    }

    @Around("@annotation(com.cat.annotations.AopTestAnno)")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        AopTestAnno aopTestAnno = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(AopTestAnno.class);
//        Method method = ClassUtils.getMostSpecificMethod(((MethodSignature) joinPoint.getSignature()).getMethod(), joinPoint.getTarget().getClass());
//        AuthZ anno = AnnotationUtils.findAnnotation(method, AuthZ.class);
//        System.out.println("anno :" + anno.value());

        System.out.println("start around");
        Object obj= (Object) joinPoint.proceed();

        System.out.println("end around");

        return obj;
    }

    @After("@annotation(com.cat.annotations.AopTestAnno)")
    public void afterMethod(JoinPoint joinPoint) throws Throwable {
        System.out.println("afterMethod !");
    }

    @AfterThrowing(pointcut = "@annotation(com.cat.annotations.AopTestAnno)", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) throws Throwable {
        System.out.println("afterThrowingMethod :"+ex.toString());
    }

    @AfterReturning(value = "@annotation(com.cat.annotations.AopTestAnno)", returning = "val")
    public void afterReturnMethod(JoinPoint joinPoint, Object val) throws Throwable {
        System.out.println("afterReturnMethod :" + val);
    }
}