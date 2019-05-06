package com.sst.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 留作日志记录使用暂未实现
 */
@Aspect
@Component
@Order(0)
public class LogAop {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String EXEC = "(execution(public * *..*Controller.*(..)) || execution(public * *..*Api.*(..)))";

    @Pointcut(value = EXEC)
    public void cutService() {
    }

    @Around("cutService()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        //先执行业务
        Object result = point.proceed();
        long endTime = System.currentTimeMillis();
        log.info("============================api响应时间:{}ms=================", endTime - startTime);
        return result;
    }

}