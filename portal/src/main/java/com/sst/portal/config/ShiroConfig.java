package com.sst.portal.config;

import com.sst.service.common.shiro.BaseShiroConfig;
import com.sst.service.common.shiro.CustomShiroFilterFactoryBean;
import com.sst.service.common.shiro.UserSessionFilter;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @Author: Ian
 * @Date: 2019/4/8
 */
@Configuration
public class ShiroConfig extends BaseShiroConfig {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new CustomShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filters = new HashMap<>();
        filters.put("userSession", new UserSessionFilter());
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/login.htm", "anon,userSession");
        filterMap.put("/**/*.css", "anon");
        filterMap.put("/**/*.js", "anon");
        filterMap.put("/**/*.html", "anon");
        filterMap.put("/img/**", "anon");
        filterMap.put("/fonts/**", "anon");
        filterMap.put("/**/favicon.ico", "anon");
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/", "user,userSession");
        filterMap.put("/sysWebsiteInfo/**", "anon");
        //只有登录才能访问用authc,登录和记住我都能访问用user
        filterMap.put("/**", "user,userSession");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/login.htm");
        return shiroFilterFactoryBean;
    }

}
