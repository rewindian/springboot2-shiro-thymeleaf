package com.sst.service.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.sst.service.system.entity.SysResource;
import com.sst.service.system.model.TreeNode;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Ian
 * @since 2019-04-08
 */
public interface SysResourceService extends IService<SysResource> {

    /**
     * 根据用户查询
     *
     * @param userId
     * @return
     */
    List<SysResource> getSysResourceByUserId(long userId);

    /**
     * 查询所有资源并构建成树形
     *
     * @return
     */
    List<TreeNode> getTree();

    /**
     * 根据角色查询
     *
     * @param roleId
     * @return
     */
    List<SysResource> getSysResourceByRoleId(long roleId);

    /**
     * 查询角色对应的资源
     *
     * @param roleIds
     * @return
     */
    List<SysResource> getRoleResources(List<Long> roleIds);

}
