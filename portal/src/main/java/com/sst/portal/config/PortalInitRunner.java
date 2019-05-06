package com.sst.portal.config;

import com.sst.core.constant.CommonConstants;
import com.sst.core.model.BaseInitRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 初始化
 */
@Component
@Slf4j
public class PortalInitRunner extends BaseInitRunner implements ApplicationRunner {


    @Resource
    private CustomConfig customConfig;

    @Resource
    private ThymeleafViewResolver thymeleafViewResolver;

    @Override
    public void run(ApplicationArguments args) {
        thymeleafConfig();
    }

    private void thymeleafConfig() {
        //添加全局静态变量
        if (thymeleafViewResolver != null) {
            Map<String, Object> vars = new HashMap<>();
            Field[] fields = CustomConfig.class.getDeclaredFields();
            if (fields.length > 0) {
                Arrays.stream(fields).forEach(field -> {
                    field.setAccessible(true);
                    Object object = null;
                    try {
                        object = field.get(customConfig);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    if (null != object)
                        vars.put(field.getName(), object);
                });
            }
            Map<String, Object> config = new HashMap<>();
            config.put(CommonConstants.GLOBAL_CONFIG, vars);
            thymeleafViewResolver.setStaticVariables(config);
        }
    }


}
