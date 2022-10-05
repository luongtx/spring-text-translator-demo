package com.demo.translator.translatordemo.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    @Before(CommonJoinPoint.SERVICE_LAYER_EXECUTION)
    public void before(JoinPoint joinPoint) {
        log.info("Request is coming through {} with parameters: {}", joinPoint, joinPoint.getArgs());
    }

    @AfterReturning(pointcut = CommonJoinPoint.DATA_LAYER_EXECUTION, returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.debug("Response from {} returned with value: {}", joinPoint, result);
    }

}
