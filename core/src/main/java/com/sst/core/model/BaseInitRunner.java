package com.sst.core.model;

import com.sst.core.config.BaseCustomConfig;
import com.sst.core.constant.CommonConstants;
import com.sst.core.util.AopTargetUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Ian
 * @Date: 2019/5/5
 */
@Slf4j
public abstract class BaseInitRunner {

//    @Resource
//    private ThymeleafViewResolver thymeleafViewResolver;
//
//    protected void thymeleafConfig(BaseCustomConfig customConfig) {
//        //添加全局静态变量
//        if (thymeleafViewResolver != null) {
//            Map<String, Object> vars = new HashMap<>();
//            try {
//                Field[] fields = AopTargetUtils.getTarget(customConfig).getClass().getDeclaredFields();
//                if (fields.length > 0) {
//                    Arrays.stream(fields).forEach(field -> {
//                        field.setAccessible(true);
//                        Object object = null;
//                        try {
//                            object = field.get(customConfig);
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//                        if (null != object)
//                            vars.put(field.getName(), object);
//                    });
//                }
//            } catch (Exception e) {
//                log.error(e.getMessage(), e);
//            }
//            Map<String, Object> config = new HashMap<>();
//            config.put(CommonConstants.GLOBAL_CONFIG, vars);
//            thymeleafViewResolver.setStaticVariables(config);
//        }
//    }
}
