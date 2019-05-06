package com.sst.service.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sst.service.system.entity.SysResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Ian
 * @since 2019-04-08
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    List<SysResource> getSysResourceByUserId(@Param("userId") long userId);

    List<SysResource> getSysResourceByRoleId(@Param("roleId") long roleId);

    List<SysResource> getRoleResources(@Param("roleIds") List<Long> roleIds);
}
