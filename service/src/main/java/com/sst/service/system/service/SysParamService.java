package com.sst.service.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.sst.service.system.entity.SysParam;

/**
 * <p>
 * 系统参数表 服务类
 * </p>
 *
 * @author Ian
 * @since 2019-04-08
 */
public interface SysParamService extends IService<SysParam> {

    String queryValueByCode(String paramCode);

}
