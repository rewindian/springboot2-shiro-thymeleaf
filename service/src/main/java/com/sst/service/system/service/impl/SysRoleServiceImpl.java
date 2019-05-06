package com.sst.service.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sst.core.constant.CommonConstants;
import com.sst.core.util.DataUtils;
import com.sst.service.system.dao.SysRoleMapper;
import com.sst.service.system.entity.SysResource;
import com.sst.service.system.entity.SysRole;
import com.sst.service.system.entity.SysRoleResource;
import com.sst.service.system.entity.SysUserRole;
import com.sst.service.system.model.SysRoleQO;
import com.sst.service.system.service.SysResourceService;
import com.sst.service.system.service.SysRoleResourceService;
import com.sst.service.system.service.SysRoleService;
import com.sst.service.system.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Ian
 * @since 2019-04-08
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysResourceService sysResourceService;

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public List<SysRole> selectRolesByUserId(long userId) {
        return baseMapper.selectRolesByUserId(userId);
    }

    @Override
    public List<SysRole> selectRolesByUserIds(List<Long> userIds) {
        return baseMapper.selectRolesByUserIds(userIds);
    }

    @Override
    public Page<SysRole> selectByPage(SysRoleQO sysRoleQO) {
        Wrapper<SysRole> ew = new EntityWrapper<SysRole>()
                .ne("role_name", CommonConstants.SUPER_ADMIN_CN).orderBy("CONVERT(role_name USING gbk)");
        if (!StringUtils.isEmpty(sysRoleQO.getRoleName())) {
            ew = new EntityWrapper<SysRole>().ne("role_name", CommonConstants.SUPER_ADMIN_CN)
                    .like("role_name", sysRoleQO.getRoleName()).orderBy("CONVERT(role_name USING gbk)");
        }
        Page<SysRole> page = selectPage(new Page<>(sysRoleQO.getCurrent(), sysRoleQO.getSize()), ew);
        List<SysRole> records = page.getRecords();
        List<SysRole> resultList = new ArrayList<>();
        if (records != null && records.size() > 0) {
            List<Long> roleIds = records.stream().map(SysRole::getId).collect(Collectors.toList());
            List<SysResource> roleResources = sysResourceService.getRoleResources(roleIds);
            Map<String, List<SysResource>> resourcesMap = DataUtils.listToMap("roleId", roleResources);
            records.forEach(sysRole -> {
                if (resourcesMap.containsKey(String.valueOf(sysRole.getId()))) {
                    sysRole.setSysResourceList(resourcesMap.get(String.valueOf(sysRole.getId())));
                }
                resultList.add(sysRole);
            });
        }
        page.setRecords(resultList);
        return page;
    }

    @Override
    @Transactional
    public boolean updateSysRole(SysRoleQO sysRoleQO) {
        boolean rel = updateById(sysRoleQO);
        if (rel) {
            List<String> resourceIds = sysRoleQO.getResourceIds();
            long roleId = sysRoleQO.getId();
            //删除该角色资源关联
            sysRoleResourceService.delete(new EntityWrapper<SysRoleResource>().eq("role_id",roleId));
            if (null != resourceIds && resourceIds.size() > 0) {
                List<SysRoleResource> sysRoleResourceList = resourceIds.stream().map(
                        resourceId -> new SysRoleResource().setResourceId(Long.valueOf(resourceId)).setRoleId(roleId)
                ).collect(Collectors.toList());
                //新增
                sysRoleResourceService.insertBatch(sysRoleResourceList);
            }
        }
        return rel;
    }

    @Override
    @Transactional
    public boolean deleteSysRole(long roleId) {
        boolean rel = deleteById(roleId);
        if (rel) {
            //删除角色与资源关联  角色与用户关联
            sysRoleResourceService.delete(new EntityWrapper<SysRoleResource>().eq("role_id", roleId));
            sysUserRoleService.delete(new EntityWrapper<SysUserRole>().eq("role_id", roleId));
        }
        return rel;
    }

    @Override
    @Transactional
    public boolean addSysRole(SysRoleQO sysRoleQO) {
        boolean rel = insert(sysRoleQO);
        if (rel) {
            //新增角色与资源关联
            List<String> resourceIds = sysRoleQO.getResourceIds();
            if (null != resourceIds && resourceIds.size() > 0) {
                List<SysRoleResource> sysRoleResourceList = resourceIds.stream().map(
                        resourceId -> new SysRoleResource().setResourceId(Long.valueOf(resourceId)).setRoleId(sysRoleQO.getId())
                ).collect(Collectors.toList());
                sysRoleResourceService.insertBatch(sysRoleResourceList);
            }
        }
        return rel;
    }
}
