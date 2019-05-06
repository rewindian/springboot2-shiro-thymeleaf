package com.sst.service.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sst.service.system.entity.SysRole;
import com.sst.service.system.model.SysRoleQO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Ian
 * @since 2019-04-08
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> selectRolesByUserId(long userId);

    List<SysRole> selectRolesByUserIds(List<Long> userIds);

    /**
     * 分页查询
     *
     * @param sysRoleQO
     * @return
     */
    Page<SysRole> selectByPage(SysRoleQO sysRoleQO);

    /**
     * 修改
     *
     * @param sysRoleQO
     * @return
     */
    boolean updateSysRole(SysRoleQO sysRoleQO);

    /**
     * 删除
     *
     * @param roleId
     * @return
     */
    boolean deleteSysRole(long roleId);

    /**
     * 新增
     *
     * @param sysRoleQO
     * @return
     */
    boolean addSysRole(SysRoleQO sysRoleQO);
}
