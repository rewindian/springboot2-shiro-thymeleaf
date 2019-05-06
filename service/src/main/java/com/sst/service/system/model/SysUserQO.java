package com.sst.service.system.model;

import com.sst.service.system.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author: Ian
 * @Date: 2019/4/11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserQO extends SysUser {

    /**
     * 每页显示条数，默认 10
     */
    private int size = 10;

    /**
     * 当前页
     */
    private int current = 1;
}
