package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    // setter logger
    private final Logger logger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
    private void forControllerPackage() {

    }

    // do the same for service
    @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
    private void forServicePackage() {

    }

    // do the same for dao
    @Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
    private void forDaoPackage() {

    }

    // combine all the pointcut together
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {
    }

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {

        // display method we are calling
        String methods = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @Before: calling method: " + methods);

        // display the arguments to the method

        // get the arguments
        Object[] args = joinPoint.getArgs();

        // loop through and display args
        for (Object tmp : args) {
            logger.info("=====>> argument: " + tmp);
        }

    }

    // add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {

        // display method we are returning from
        String methods = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @AfterReturning: from method " + methods);

        // display data returned
        logger.info("=====>> result: " + result);
    }
}
