package com.jcy.tradingstrategies.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ControllerLogAop {

    @Pointcut("execution(* com.jcy.tradingstrategies.controller..*(..))")
    public void controller() {
    }

    ;

    @Around("controller()")
    private Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        log.info("- - - - - - - - - 处理请求开始 - - - - - - - - -");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestUrl = request.getRequestURL().toString();
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        log.info("\n[请求地址]:{}\n[目标类]:{}\n[目标方法]:{}\n[请求参数]:{}", requestUrl, className, methodName, Arrays.toString(args));
        try {
            Object obj = pjp.proceed();
            return obj;
        } finally {
            log.info("- - - - - - - - -  处理请求结束 - - - - - - - - -");
        }

    }
}
