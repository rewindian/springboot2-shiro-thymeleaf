package com.sst.admin.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sst.core.constant.CommonConstants;
import com.sst.core.model.ResponseDomain;
import com.sst.core.validator.ValidatorUtils;
import com.sst.core.validator.group.AddGroup;
import com.sst.core.validator.group.UpdateGroup;
import com.sst.service.system.entity.SysUser;
import com.sst.service.system.model.SysUserQO;
import com.sst.service.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: Xu Haidong
 * @Date: 2019/4/11
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页查询
     *
     * @param sysUserQO
     * @return
     */
    @GetMapping("/query")
    @ResponseBody
    public ResponseDomain query(SysUserQO sysUserQO) {
        return ResponseDomain.getSuccessResponse().setData(sysUserService.selectByPage(sysUserQO));
    }

    /**
     * 新增用户
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public ResponseDomain add(@RequestBody SysUser sysUser) {
        //参数校验
        ValidatorUtils.validateEntity(sysUser, AddGroup.class);
        if (sysUserService.addSysUser(sysUser)) {
            return ResponseDomain.getSuccessResponse();
        } else {
            return ResponseDomain.getFailedResponse();
        }
    }

    /**
     * 修改用户信息
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public ResponseDomain update(@RequestBody SysUser sysUser) {
        ValidatorUtils.validateEntity(sysUser, UpdateGroup.class);
        if (sysUserService.updateSysUser(sysUser)) {
            return ResponseDomain.getSuccessResponse();
        } else {
            return ResponseDomain.getFailedResponse();
        }
    }

    /**
     * 启用/禁用
     *
     * @param userId
     * @param status 1：启用；0：禁用
     * @return
     */
    @GetMapping("/updateStatus")
    @ResponseBody
    public ResponseDomain updateStatus(@RequestParam String userId, @RequestParam String status) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(status)) {
            return ResponseDomain.getMissingParameters();
        }
        int stat = CommonConstants.UserStatus.DISABLED;
        if ("1".equals(status)) {
            stat = CommonConstants.UserStatus.AVAILABLE;
        }
        if (sysUserService.updateUserStatus(Long.valueOf(userId), stat)) {
            return ResponseDomain.getSuccessResponse();
        } else {
            return ResponseDomain.getFailedResponse();
        }
    }

    /**
     * 验证用户名是否已存在
     *
     * @param username
     * @return
     */
    @GetMapping("/validateUsername")
    @ResponseBody
    public ResponseDomain validateUsername(@RequestParam String username) {
        if (StringUtils.isEmpty(username)) {
            return ResponseDomain.getMissingParameters();
        }
        int count = sysUserService.selectCount(new EntityWrapper<SysUser>().eq("username", username));
        if (count > 0) {
            return ResponseDomain.getSuccessResponse().setData(1);
        } else {
            return ResponseDomain.getSuccessResponse().setData(0);
        }
    }

    /**
     * 重置密码
     *
     * @param userId
     * @return
     */
    @GetMapping("/resetPassword")
    @ResponseBody
    public ResponseDomain resetPassword(@RequestParam String userId) {
        if (StringUtils.isEmpty(userId)) {
            return ResponseDomain.getMissingParameters();
        }
        if (sysUserService.resetPassword(Long.valueOf(userId))) {
            return ResponseDomain.getSuccessResponse();
        } else {
            return ResponseDomain.getFailedResponse();
        }
    }

    @GetMapping("/list.htm")
    public String list() {
        return "system/sysUser";
    }

}
