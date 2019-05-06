package com.sst.admin.config;

import com.sst.core.aop.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * 全局异常处理
 */
@ControllerAdvice
@Slf4j
@Order(-1)
public class CustomExceptionHandler extends GlobalExceptionHandler {

    @Autowired
    private CustomConfig customConfig;

    @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorizedException(UnauthorizedException e) {
        log.error(e.getMessage(), e);
        //跳转登录页
        return "redirect:" + customConfig.getLoginUrl();
    }

}
