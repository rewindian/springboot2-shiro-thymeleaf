package com.sst.core.aop;

import com.sst.core.exception.CustomException;
import com.sst.core.model.ResponseDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @Author: Ian
 * @Date: 2019/4/4
 */
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 自定义异常
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseDomain handleCustomException(CustomException e) {
        log.error(e.getMessage(), e);
        return ResponseDomain.getFailedResponse().setResultDesc(e.getMessage());
    }

    /**
     * 违反数据库唯一键
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public ResponseDomain handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return ResponseDomain.getFailedResponse().setResultDesc("数据库已经存在该记录");
    }

    /**
     * 数据库字段数据过长
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseDomain handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error(e.getMessage(), e);
        if (null != e.getMessage() && e.getMessage().contains("Data too long for column")) {
            String column = e.getMessage().substring(
                    e.getMessage().lastIndexOf("Data too long for column '") + 26
                    , e.getMessage().lastIndexOf("' at row"));
            return ResponseDomain.getFailedResponse().setResultDesc(column + "字段过长");
        } else {
            return ResponseDomain.getFailedResponse().setResultDesc(e.getMessage());
        }
    }

    /**
     * 参数校验
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseDomain handleConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        if (!violations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<?> constraint : violations) {
                msg.append(constraint.getMessage()).append("<br>");
            }
            return ResponseDomain.getFailedResponse().setResultDesc(msg.toString());
        } else {
            return ResponseDomain.getFailedResponse().setResultDesc(e.getMessage());
        }
    }

    /**
     * 未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseDomain handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseDomain.getFailedResponse().setResultDesc(e.getMessage());
    }
}
