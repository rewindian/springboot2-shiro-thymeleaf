package com.sst.service.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sst.service.system.dao.SysWebsiteInfoMapper;
import com.sst.service.system.entity.SysWebsiteInfo;
import com.sst.service.system.service.SysWebsiteInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 站点详情 服务实现类
 * </p>
 *
 * @author Ian
 * @since 2019-04-09
 */
@Service
public class SysWebsiteInfoServiceImpl extends ServiceImpl<SysWebsiteInfoMapper, SysWebsiteInfo> implements SysWebsiteInfoService {

    @Override
    public SysWebsiteInfo getWebsiteInfo() {
        return baseMapper.getWebsiteInfo();
    }

    @Override
    @Transactional
    public void saveWebsiteInfo(SysWebsiteInfo sysWebsiteInfo) {
        //先将所有的站点信息置为失效
        baseMapper.dropAllRecord();

        //保存当前站点信息
        baseMapper.insert(sysWebsiteInfo);
    }
}
