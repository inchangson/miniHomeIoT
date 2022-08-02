package com.ktspace.miniHomeIoT.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTraceAop {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Around("execution(* com.ktspace.miniHomeIoT..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        logger.info("START: " + joinPoint.toString());

        try{
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            logger.info("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
