package com.sst.service.common.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.sst.core.constant.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: Ian
 * @Date: 2019/4/8
 */
@Slf4j
public class BaseShiroConfig {

    private static final String SECRET_KEY = "sst1234";

    private static final String COOKIE_PATH = "/";

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 自定义的Realm
     */
    @Bean(name = "customShiroRealm")
    public CustomShiroRealm customShiroRealm() {
        CustomShiroRealm customShiroRealm = new CustomShiroRealm();
        return customShiroRealm;
    }


    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customShiroRealm());
        securityManager.setRememberMeManager(rememberMeManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(redisCacheManager());
        return securityManager;
    }


    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisProperties.getHost());
//        redisManager.setPassword(redisProperties.getPassword());
        redisManager.setDatabase(redisProperties.getDatabase());
        redisManager.setPort(redisProperties.getPort());
        return redisManager;
    }


    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }


    @Bean
    public DefaultWebSessionManager sessionManager() {
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(simpleCookie());
        return sessionManager;
    }

    /**
     * 保存sessionId的cookie
     *
     * @return
     */
    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName(CommonConstants.SHIRO_COOKIE);
        simpleCookie.setPath(COOKIE_PATH);
        return simpleCookie;
    }

    /**
     * 记住我的cookie对象;
     *
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName(CommonConstants.SHIRO_REMEMBER_ME);
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        simpleCookie.setPath(COOKIE_PATH);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        byte[] bytesOfMessage = null;
        MessageDigest md = null;
        try {
            bytesOfMessage = SECRET_KEY.getBytes("UTF-8");
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        byte[] b = md.digest(bytesOfMessage);
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(b);
        return cookieRememberMeManager;
    }

    /**
     * 开启shiro注解
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
                = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

}
