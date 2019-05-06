package com.sst.service.system.service;


import com.sst.service.system.entity.SysUser;

import java.util.Set;

/**
 * @Author: Ian
 * @Date: 2019/4/8
 */
public interface ShiroService {

    /**
     * 查询用户权限
     *
     * @param sysUser
     * @return
     */
    Set<String> getUserPerms(SysUser sysUser);

    /**
     * 查询用户角色
     *
     * @param sysUser
     * @return
     */
    Set<String> getUserRoles(SysUser sysUser);
}
