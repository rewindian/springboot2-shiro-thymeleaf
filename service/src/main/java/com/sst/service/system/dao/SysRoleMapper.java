package com.sst.service.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sst.service.system.entity.SysRole;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> selectRolesByUserId(@Param("userId") long userId);

    List<SysRole> selectRolesByUserIds(@Param("userIds") List<Long> userIds);
}
