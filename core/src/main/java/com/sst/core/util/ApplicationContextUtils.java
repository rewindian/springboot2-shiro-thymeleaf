package com.sst.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * 初始化ApplicationContext
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext ac = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        if (ac == null) {
            throw new RuntimeException("applicationContext init failed!");
        }
        return ac;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) getApplicationContext().getBean(name);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static Resource getResource(String location) {
        return getApplicationContext().getResource(location);
    }

}
