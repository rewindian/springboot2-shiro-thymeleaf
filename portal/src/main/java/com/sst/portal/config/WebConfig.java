package com.sst.portal.config;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


/**
 * <p>
 * WEB 初始化相关配置
 * </p>
 *
 * @author xhd
 * @since 2018-6-7
 */
@ControllerAdvice
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        ObjectMapper objectMapper = builder.build();
        objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true); // 忽略 transient 修饰的属性
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
    }

}
