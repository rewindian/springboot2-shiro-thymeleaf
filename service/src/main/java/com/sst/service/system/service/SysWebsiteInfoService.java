package com.sst.service.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.sst.service.system.entity.SysWebsiteInfo;

/**
 * <p>
 * 站点详情 服务类
 * </p>
 */
public interface SysWebsiteInfoService extends IService<SysWebsiteInfo> {

    /**
     * @description 获取站点信息
     */
    SysWebsiteInfo getWebsiteInfo();

    /**
     * @description 保存站点信息
     */
    void saveWebsiteInfo(SysWebsiteInfo sysWebsiteInfo);
}
