package com.mlk.home.aop;


import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


//申明是个切面
@Aspect
//申明是个spring管理的bean
@Component
@Order(1)
public class AspectDemo {


    private Logger log = Logger.getLogger(getClass());

    //申明一个切点 里面是 execution表达式
    @Pointcut("execution(* com.mlk.home.controller..*.*(..))")
    private void controllerAspect() {
    }


    //请求method前打印内容
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //打印请求内容
        log.info("===============请求内容===============");
        log.info("请求地址:" + request.getRequestURL().toString());
        log.info("请求方式:" + request.getMethod());
        log.info("请求类方法:" + joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length >= 1 && args[0] != null) {
            Object objPar = args[0];
            try {
                log.info("请求类方法参数:" + JSON.toJSONString(objPar));
            }catch (Exception e){
                log.error("请求类方法参数转换异常：参数"+objPar.toString()+" error{}",e);
            }
        }
        log.info("===============请求内容===============");
    }


    //在方法执行完结后打印返回内容
    @AfterReturning(returning = "o", pointcut = "controllerAspect()")
    public void methodAfterReturing(Object o) {
        log.info("--------------返回内容----------------");
        log.info("Response内容:" + JSON.toJSONString(o));
        log.info("--------------返回内容----------------");
    }
}
