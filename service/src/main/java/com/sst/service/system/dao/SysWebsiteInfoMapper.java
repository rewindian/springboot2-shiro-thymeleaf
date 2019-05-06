package com.sst.service.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sst.service.system.entity.SysWebsiteInfo;

/**
 * <p>
 * 站点详情 Mapper 接口
 * </p>
 *
 * @author Ian
 * @since 2019-04-09
 */
public interface SysWebsiteInfoMapper extends BaseMapper<SysWebsiteInfo> {

    SysWebsiteInfo getWebsiteInfo();

    /**
     * @description 软删除所有站点信息
     * @author Ian
     * @date 2019/4/10 16:57
     * @param
     * @return void
     */
    void dropAllRecord();
}
