package com.sst.service.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sst.service.system.dao.SysParamMapper;
import com.sst.service.system.entity.SysParam;
import com.sst.service.system.service.SysParamService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统参数表 服务实现类
 * </p>
 *
 * @author Ian
 * @since 2019-04-08
 */
@Service
public class SysParamServiceImpl extends ServiceImpl<SysParamMapper, SysParam> implements SysParamService {

    public String queryValueByCode(String paramCode) {
        return baseMapper.queryValueByCode(paramCode);
    }

}
