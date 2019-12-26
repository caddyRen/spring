package indi.ikun.spring.demo.service.demo.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName DemoAspect
 * @Description TODO
 * @Author caddyR
 * @Date 2019-08-29 17:19
 * @Version 1.0
 **/
@Aspect
@Component
@Slf4j
public class DemoAspect {
//    @Pointcut("execution(public * com.mumu.springcloud.demo.service.demo.controller.TestController.*(..))")
//    public void addAdvice(){}
@Pointcut("@annotation(indi.ikun.spring.demo.service.demo.aop.aspect.DemoAnnotation)")
public void addAdvice(){}

    @Before("addAdvice()")
    public void before(JoinPoint joinPoint){
        Object[] args=joinPoint.getArgs();
        HttpServletRequest requests=(HttpServletRequest) args[0];
        log.info("start");
        log.info(requests.getRequestURL().toString());
        //log.info("end");
    }
    @After("addAdvice()")
    public void after(JoinPoint joinPoint){
        Object[] args=joinPoint.getArgs();
        HttpServletRequest requests=(HttpServletRequest) args[0];
        //log.info("start");
        log.info(requests.getRequestURL().toString());
        log.info("end");
    }
    @Around("addAdvice()")
    public void around(JoinPoint joinPoint){
        Object[] args=joinPoint.getArgs();
        HttpServletRequest requests=(HttpServletRequest) args[0];
        //log.info("start");
        log.info(requests.getRequestURL().toString());
        //log.info("end");
    }

}
