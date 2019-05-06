package com.sst.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import java.util.Collections;

/**
 * @Author: Ian
 * @Date: 2019/4/9
 */
@SpringBootApplication
@ComponentScan({"com.sst"})
@EnableCaching
public class AdminApplication extends SpringBootServletInitializer {

    private static Class<AdminApplication> appClass = AdminApplication.class;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(appClass);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
        SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
        sessionCookieConfig.setHttpOnly(true);
    }

    public static void main(String[] args) {
        SpringApplication.run(appClass, args);
    }
}
