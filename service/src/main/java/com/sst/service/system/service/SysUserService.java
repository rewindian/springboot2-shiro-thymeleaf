package com.sst.service.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sst.service.system.entity.SysUser;
import com.sst.service.system.model.SysUserQO;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author Ian
 * @since 2019-04-04
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 修改密码
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    boolean updatePassword(long userId, String oldPassword, String newPassword);

    /**
     * 重置密码
     *
     * @param userId
     * @return
     */
    boolean resetPassword(long userId);

    /**
     * 新增用户
     *
     * @param sysUser
     * @return
     */
    boolean addSysUser(SysUser sysUser);

    /**
     * 禁用/启用用户
     *
     * @param userId
     * @return
     */
    boolean updateUserStatus(long userId, int status);

    /**
     * 修改用户信息
     *
     * @return
     */
    boolean updateSysUser(SysUser sysUser);

    /**
     * 自定义分页查询
     *
     * @param sysUserQO
     * @return
     */
    Page<SysUser> selectByPage(SysUserQO sysUserQO);

}
