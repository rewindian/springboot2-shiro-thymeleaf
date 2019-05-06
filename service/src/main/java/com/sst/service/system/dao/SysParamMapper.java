package com.sst.service.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sst.service.system.entity.SysParam;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统参数表 Mapper 接口
 * </p>
 *
 * @author Ian
 * @since 2019-04-08
 */
public interface SysParamMapper extends BaseMapper<SysParam> {

    String queryValueByCode(@Param("paramCode") String paramCode);

}
