package com.sst.admin.controller;

import com.sst.service.system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @Author: Ian
 * @Date: 2019/5/5
 */
@Controller
@Slf4j
public class IndexController {

    @GetMapping("/index.htm")
    public String initIndex() {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            SysUser user = (SysUser) subject.getPrincipal();
            if (null != user) {
                log.info(user.getRealName());
            }
        }
        return "index";
    }
}
