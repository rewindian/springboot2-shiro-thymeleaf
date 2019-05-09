package com.sst.admin.controller;


import com.sst.core.model.ResponseDomain;
import com.sst.service.system.entity.SysUser;
import com.sst.service.system.service.ShiroService;
import com.sst.service.system.service.SysResourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author XuHaidong
 * @since 2019-04-08
 */
@Controller
@RequestMapping("/sysResource")
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    @Autowired
    private ShiroService shiroService;

    @RequestMapping(value = "/queryTree", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDomain queryTree() {
        return ResponseDomain.getSuccessResponse().setData(sysResourceService.getTree());
    }

    @GetMapping("/queryByUserId")
    @ResponseBody
    public ResponseDomain queryByUserId() {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            SysUser user = (SysUser) subject.getPrincipal();
            Set<String> perms = shiroService.getUserPerms(user);
            return ResponseDomain.getSuccessResponse().setData(perms);
        } else {
            throw new UnauthorizedException("用户未登录");
        }
    }

}

