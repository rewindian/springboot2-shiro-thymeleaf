package com.sst.service.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sst.core.util.DataUtils;
import com.sst.service.system.dao.SysResourceMapper;
import com.sst.service.system.entity.SysResource;
import com.sst.service.system.model.TreeNode;
import com.sst.service.system.service.SysResourceService;
import org.springframework.stereotype.Service;

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
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {

    @Override
    public List<SysResource> getSysResourceByUserId(long userId) {
        return baseMapper.getSysResourceByUserId(userId);
    }

    @Override
    public List<TreeNode> getTree() {
        List<SysResource> sysResourceList = baseMapper.selectList(new EntityWrapper<>());
        List<TreeNode> resultList = new ArrayList<>();
        if (null != sysResourceList && sysResourceList.size() > 0) {
            Map<String, List<SysResource>> map = DataUtils.listToMap("parentId", sysResourceList);
            resultList = sysResourceList.stream()
                    .filter(sysResource -> sysResource.getParentId() == 0)
                    .map(sysResource ->
                            new TreeNode().setId(String.valueOf(sysResource.getId()))
                                    .setPid(String.valueOf(sysResource.getParentId()))
                                    .setName(sysResource.getResourceName())
                                    .setResourceType(sysResource.getResourceType())
                                    .setOpen(true)
                    ).collect(Collectors.toList());
            findChildren(map, resultList);
        }
        return resultList;
    }

    @Override
    public List<SysResource> getSysResourceByRoleId(long roleId) {
        return baseMapper.getSysResourceByRoleId(roleId);
    }

    @Override
    public List<SysResource> getRoleResources(List<Long> roleIds) {
        return baseMapper.getRoleResources(roleIds);
    }

    private void findChildren(Map<String, List<SysResource>> map, List<TreeNode> target) {
        target.forEach(sysResource -> {
            String id = String.valueOf(sysResource.getId());
            if (map.containsKey(id)) {
                List<TreeNode> children = map.get(id).stream().map(resource ->
                        new TreeNode().setId(String.valueOf(resource.getId()))
                                .setPid(String.valueOf(resource.getParentId()))
                                .setName(resource.getResourceName())
                                .setResourceType(resource.getResourceType())
                ).collect(Collectors.toList());
                sysResource.setChildren(children);
                findChildren(map, children);
            }
        });
    }
}
