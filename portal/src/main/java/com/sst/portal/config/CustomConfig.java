package com.sst.portal.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Ian
 * @Date: 2019/4/4
 */
@Configuration
@ConfigurationProperties(prefix = "custom")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomConfig {


    /**
     * 后台首页地址
     */
    private String adminIndex;

    private String frontendPrefix;

}
