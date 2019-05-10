package com.sst.portal.config;

import com.sst.core.aop.GlobalExceptionHandler;
import com.sst.core.model.ResponseDomain;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.UnknownSessionException;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 可以在项目自行继承该类实现全局异常处理
 */
@ControllerAdvice
@Slf4j
@Order(-1)
public class CustomExceptionHandler extends GlobalExceptionHandler {


    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ResponseDomain handleUnauthorizedException(UnauthorizedException e) {
        log.error(e.getMessage(), e);
        return ResponseDomain.getFailedResponse().setResult("401");
    }

    @ExceptionHandler(UnknownSessionException.class)
    @ResponseBody
    public ResponseDomain handleUnknownSessionException(UnknownSessionException e) {
        log.error(e.getMessage(), e);
        return ResponseDomain.getFailedResponse().setResult("401");
    }

}
