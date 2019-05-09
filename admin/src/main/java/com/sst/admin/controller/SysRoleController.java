package com.sst.admin.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sst.core.model.ResponseDomain;
import com.sst.core.validator.ValidatorUtils;
import com.sst.core.validator.group.AddGroup;
import com.sst.core.validator.group.UpdateGroup;
import com.sst.service.system.entity.SysRole;
import com.sst.service.system.model.SysRoleQO;
import com.sst.service.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author XuHaidong
 * @since 2019-04-08
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 分页查询
     *
     * @param sysRoleQO
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDomain query(SysRoleQO sysRoleQO) {
        return ResponseDomain.getSuccessResponse().setData(sysRoleService.selectByPage(sysRoleQO));
    }

    /**
     * 新增
     *
     * @param sysRoleQO
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDomain add(@RequestBody SysRoleQO sysRoleQO) {
        ValidatorUtils.validateEntity(sysRoleQO, AddGroup.class);
        if (sysRoleService.addSysRole(sysRoleQO)) {
            return ResponseDomain.getSuccessResponse();
        } else {
            return ResponseDomain.getFailedResponse();
        }
    }

    /**
     * 编辑
     *
     * @param sysRoleQO
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDomain update(@RequestBody SysRoleQO sysRoleQO) {
        ValidatorUtils.validateEntity(sysRoleQO, UpdateGroup.class);
        if (sysRoleService.updateSysRole(sysRoleQO)) {
            return ResponseDomain.getSuccessResponse();
        } else {
            return ResponseDomain.getFailedResponse();
        }
    }

    /**
     * 删除
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDomain delete(@RequestParam String roleId) {
        if (sysRoleService.deleteSysRole(Long.valueOf(roleId))) {
            return ResponseDomain.getSuccessResponse();
        } else {
            return ResponseDomain.getFailedResponse();
        }
    }

    @RequestMapping(value = "/list.htm", method = RequestMethod.GET)
    public String list() {
        return "system/sysRole";
    }

    /**
     * 查询全部
     *
     * @return
     */
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDomain queryAll() {
        return ResponseDomain.getSuccessResponse().setData(sysRoleService.selectList(
                new EntityWrapper<SysRole>().orderBy("CONVERT(role_name USING gbk)")));
    }

    /**
     * 验证角色名称是否存在
     *
     * @param roleName
     * @return
     */
    @GetMapping("/validateRoleName")
    @ResponseBody
    public ResponseDomain validateUsername(@RequestParam String roleName) {
        if (StringUtils.isEmpty(roleName)) {
            return ResponseDomain.getMissingParameters();
        }
        int count = sysRoleService.selectCount(new EntityWrapper<SysRole>().eq("role_name", roleName));
        if (count > 0) {
            return ResponseDomain.getSuccessResponse().setData(1);
        } else {
            return ResponseDomain.getSuccessResponse().setData(0);
        }
    }
}

