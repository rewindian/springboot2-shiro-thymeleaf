package com.sst.portal.config;

import com.sst.core.aop.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * 可以在项目自行继承该类实现全局异常处理
 */
@ControllerAdvice
@Slf4j
@Order(-1)
public class CustomExceptionHandler extends GlobalExceptionHandler {


    @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorizedException(UnauthorizedException e) {
        log.error(e.getMessage(), e);
        //跳转登录页
        return "redirect:/login.htm";
    }

}
