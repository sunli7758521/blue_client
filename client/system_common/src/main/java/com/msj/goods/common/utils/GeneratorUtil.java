package com.msj.goods.common.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sun li @Date 2018/11/4 16:33 @Description mybatis-plus 代码生成器
 */
public class GeneratorUtil {
    /**
     * 模块名称
     */
    public static String MODULE_NAME = "system_common";
    /**
     * 数据库名称
     */
    public static String DB_URL = "jdbc:mysql://192.168.31.83:3306/meisijia";

    /** public static String DB_URL = "jdbc:oracle:thin:@172.16.10.66:1521:orcl"; //数据库名称 */

    /**
     * public static String DB_USER = "pacs"; //数据库用户 public static String DB_PASS = "123456"; //数据库密码
     * // public static String DB_DRIVER = "com.mysql.jdbc.Driver"; //数据库驱动名称 public static String
     * DB_DRIVER = "oracle.jdbc.driver.OracleDriver"; //数据库驱动名称
     */
    public static void main(String[] args) {
        String packageName = "com.msj.goods";
        /** user -> UserService, 设置成true: user -> IUserService */
        boolean serviceNameStartWithI = false;
        String[] sts = new String[]{"integral_app_user"};
        generateByTables(serviceNameStartWithI, packageName, sts);
    }

    private static void generateByTables(
            boolean serviceNameStartWithI, String packageName, String... tableNames) {
    /*
    全局配置，如有需求请查阅官方文档 http://mp.baomidou.com/#/?id=%E7%AE%80%E4%BB%8B
     */
        GlobalConfig config = new GlobalConfig();
        config
                .setActiveRecord(true)
                .setOpen(false)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setEnableCache(false)
                .setAuthor("sunli")
                //                .setOutputDir("d:\\test")
                .setOutputDir(MODULE_NAME + "\\src\\main\\java")
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }

    /*
    数据源配置 如有需求请查阅官方文档 http://mp.baomidou.com/#/?id=%E7%AE%80%E4%BB%8B
     */
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig
                .setDbType(DbType.MYSQL)
                .setUrl(DB_URL)
                .setUsername("root")
                .setPassword("msjkj")
                .setDriverName("com.mysql.jdbc.Driver");

    /*
    策略配置 如有需求请查阅官方文档 http://mp.baomidou.com/#/?id=%E7%AE%80%E4%BB%8B
     */
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                //                .setTablePrefix("sys")
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames); // 修改替换成你需要的表名，多个表名传数组

    /*
    注入配置，这里是为了修改XML文件生成目录
    如有需求请查阅官方文档 http://mp.baomidou.com/#/?id=%E7%AE%80%E4%BB%8B
     */
        InjectionConfig injectionConfig =
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        this.setMap(map);
                    }
                };
        List<FileOutConfig> fileOutConfigs = new ArrayList<>();
        fileOutConfigs.add(
                new FileOutConfig("/templates/mapper.xml.ftl") {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return MODULE_NAME
                                + "\\src\\main\\resources\\mapper\\"
                                + tableInfo.getEntityName()
                                + "Mapper.xml";
                    }
                });
        injectionConfig.setFileOutConfigList(fileOutConfigs);

    /*
    生成
     */
        new AutoGenerator()
                .setGlobalConfig(config)
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setCfg(injectionConfig)
                .setTemplate(new TemplateConfig().setXml(null).setController(null))
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                //                              .setController("controller")
                                .setEntity("entity"))
                .execute();
    }

    private void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
  }
}
