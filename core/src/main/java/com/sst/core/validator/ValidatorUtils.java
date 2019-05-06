package com.sst.core.validator;


import com.sst.core.exception.CustomException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @Author: Ian
 * @Date: 2019/4/11
 */
public class ValidatorUtils {

    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws CustomException 校验不通过，则报异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws CustomException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append(",");
            }
            throw new CustomException(msg.toString().substring(0, msg.toString().length() - 1));
        }
    }
}
