package com.sst.service.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sst.core.constant.CommonConstants;
import com.sst.service.system.entity.SysResource;
import com.sst.service.system.entity.SysRole;
import com.sst.service.system.entity.SysUser;
import com.sst.service.system.service.ShiroService;
import com.sst.service.system.service.SysParamService;
import com.sst.service.system.service.SysResourceService;
import com.sst.service.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: Ian
 * @Date: 2019/4/8
 */
@Service
@Slf4j
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysResourceService sysResourceService;

    @Autowired
    private SysParamService sysParamService;

    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public Set<String> getUserPerms(SysUser sysUser) {
        String adminUsername = sysParamService.queryValueByCode(CommonConstants.SUPER_ADMIN);
        Set<String> permsSet = new HashSet<>();
        List<SysResource> sysResourceList;
        if (adminUsername.equals(sysUser.getUsername())) { //如果是超级管理员 查询所有权限
            sysResourceList = sysResourceService.selectList(new EntityWrapper<>());
        } else {
            Long userId = sysUser.getId();
            sysResourceList = sysResourceService.getSysResourceByUserId(userId);
        }
        if (null != sysResourceList && sysResourceList.size() > 0) {
            //用户权限列表
            permsSet = sysResourceList.stream()
                    .filter(sysResource -> !StringUtils.isEmpty(sysResource.getPermission()))
                    .map(SysResource::getPermission)
                    .collect(Collectors.toSet());
        }
        return permsSet;
    }

    @Override
    public Set<String> getUserRoles(SysUser sysUser) {
        List<SysRole> list = sysRoleService.selectRolesByUserId(sysUser.getId());
        Set<String> roleSet = new HashSet<>();
        if (null != list && list.size() > 0) {
            roleSet = list.stream().filter(sysRole -> !StringUtils.isEmpty(sysRole.getRoleName()))
                    .map(SysRole::getRoleName).collect(Collectors.toSet());
        }
        return roleSet;
    }
}
