package com.sst.core.multidatasource;


import java.lang.annotation.*;

/**
 * @Author: Ian
 * @Date: 2019/4/10
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    String value();
}
