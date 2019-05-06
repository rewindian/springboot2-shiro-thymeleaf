package com.sst.portal.controller;

import com.sst.core.constant.CommonConstants;
import com.sst.core.model.ResponseDomain;
import com.sst.portal.config.CustomConfig;
import com.sst.service.system.entity.SysUser;
import com.sst.service.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * @Author: Ian
 * @Date: 2019/4/8
 */
@Controller
@Slf4j
public class UserLoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private CustomConfig customConfig;

    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 用户登录
     *
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseDomain doLogin(@RequestBody SysUser sysUser) {
        Subject subject = SecurityUtils.getSubject();
        String username = sysUser.getUsername();
        UsernamePasswordToken token = new UsernamePasswordToken(username, sysUser.getPassword(), sysUser.isRememberMe());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            log.error(e.getMessage(), e);
            token.clear();
            return ResponseDomain.getFailedResponse().setResultDesc(e.getMessage());
        }

        return ResponseDomain.getSuccessResponse().setResultDesc("登录成功");
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDomain logout() {
        SecurityUtils.getSubject().logout();
        return ResponseDomain.getSuccessResponse();
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDomain updatePassword(@RequestBody Map<String, String> params) {
        String oldPassword = params.get("oldPassword");
        String newPassword = params.get("newPassword");
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            SysUser currentUser = (SysUser) subject.getPrincipal();
            if (sysUserService.updatePassword(currentUser.getId(), oldPassword, newPassword)) {
                subject.logout();
//                UsernamePasswordToken token = new UsernamePasswordToken(currentUser.getUsername(), newPassword);
//                subject.login(token);
                return ResponseDomain.getSuccessResponse();
            } else {
                return ResponseDomain.getFailedResponse();
            }
        } else {
            throw new UnauthorizedException("用户未登录");
        }
    }

    @RequestMapping(value = "/toAdmin.htm", method = RequestMethod.GET)
    public String toAdmin() {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            SysUser user = (SysUser) subject.getSession().getAttribute(CommonConstants.SESSION_USER_INFO);
            log.info(user.getRealName());
            String adminIndex = customConfig.getAdminIndex() + "index.htm";
            return "redirect:" + adminIndex;
        } else {
            throw new UnauthorizedException("用户未登录");
        }
    }
}
