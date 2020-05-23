package com.github.smalnote.heron.spring.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AspectConfiguration {

    private final Log logger = LogFactory.getLog(AspectConfiguration.class);

    @Pointcut("execution(* com.github.smalnote.heron.spring.wke.WkeService.call(..))")
    public void wkeCall() {
    }

    @Around("wkeCall()")
    public Object aroundWkeCall(ProceedingJoinPoint joinPoint) throws Throwable {
        if (logger.isDebugEnabled()) {
            logger.debug("wke call with [" + joinPoint.getArgs()[0] + "]");
        }
        Object retVal = joinPoint.proceed();
        if (logger.isDebugEnabled()) {
            logger.debug("wke call return with [" + retVal + "]");
        }
        return retVal;
    }

}
