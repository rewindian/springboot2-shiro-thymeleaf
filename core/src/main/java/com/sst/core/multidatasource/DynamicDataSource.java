package com.sst.core.multidatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author: Ian
 * @Date: 2019/4/10
 * AbstractRoutingDataSource(每执行一次数据库，动态获取DataSource)
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
