package com.sst.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * mybatis-plus代码生成器
 */
public class GeneratorServiceEntity {
    /**
     * 作者署名
     */
    private static final String AUTHOR = "test";

    /**
     * 数据库连接URL
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test?useSSL=false";
    /**
     * 数据库连接用户名
     */
    private static final String USERNAME = "root";

    /**
     * 数据库连接密码
     */
    private static final String PASSWORD = "123456";
    /**
     * 数据库连接驱动
     */
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

    /**
     * 数据库类型
     */
    private static final DbType DB_TYPE = DbType.MYSQL;

    /**
     * 代码文件生成路径
     */
    private static final String OUTPUT_DIR = "F:\\codeGen\\mybatisPlus";

    /**
     * 生成文件所在包名
     */
    private static final String PACKAGE_NAME = "com.sst.service.system";

    /**
     * WEB层超类
     */
    private static final String SUPER_CONTROLLER = "";

    /**
     * 实体类超类
     */
    private static final String SUPER_ENTITY = "";


    public void generateCode(String[] tables) {
        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
        generateByTables(serviceNameStartWithI, PACKAGE_NAME, tables);
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DB_TYPE)
                .setUrl(DB_URL)
                .setUsername(USERNAME)
                .setPassword(PASSWORD)
                .setDriverName(DRIVER_NAME);
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setSuperControllerClass(SUPER_CONTROLLER)
                .setRestControllerStyle(false)
//                .setSuperEntityClass(SUPER_ENTITY)
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
//                .setTablePrefix("q_")   //要移除的前缀
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(true)
                .setAuthor(AUTHOR)
                .setOutputDir(OUTPUT_DIR)
                .setFileOverride(true)
                .setEnableCache(false);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                                .setMapper("dao")
                                .setXml("mapping")
                )
                .execute();
    }

}
