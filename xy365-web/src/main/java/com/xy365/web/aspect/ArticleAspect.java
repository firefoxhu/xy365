package com.xy365.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ArticleAspect {

    @Pointcut("execution(* com.xy365.web.controller.ArticleController.findArticle(..))")
    public void cut(){}

    @Around("cut()")
    public Object twiceAsOld(ProceedingJoinPoint joinPoint){

        // TODO 从此验证用户是否登录

        try {
            long start = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long timeConsuming = System.currentTimeMillis() - start;
            System.out.println("本次请求耗费了："+timeConsuming+"毫秒");
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }


}
