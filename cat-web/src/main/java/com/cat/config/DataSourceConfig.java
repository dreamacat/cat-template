package com.cat.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;



/**
 * @author wangxiaoqiang on 2017/7/6.
 */
@Configuration
@PropertySource("classpath:properties/jdbc.properties")
@EnableTransactionManagement
public class DataSourceConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(getDataSource());
    }

    @Bean(name = "dataSource", destroyMethod = "close", initMethod = "init")
    public DataSource getDataSource() {

        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(env.getProperty("jdbc.url"));
        datasource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        datasource.setUsername(env.getProperty("jdbc.username"));
        datasource.setPassword(env.getProperty("jdbc.password"));
        datasource.setInitialSize(Integer.parseInt(env.getProperty("jdbc.initialSize")));
        datasource.setMinIdle(Integer.parseInt(env.getProperty("jdbc.minIdle")));
        datasource.setMaxActive(Integer.parseInt(env.getProperty("jdbc.maxActive")));
        datasource.setMaxWait(Integer.parseInt(env.getProperty("jdbc.maxWaitMillis")));
        datasource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(env.getProperty("jdbc.timeBetweenEvictionRunsMillis")));
        datasource.setMinEvictableIdleTimeMillis(Integer.parseInt(env.getProperty("jdbc.minEvictableIdleTimeMillis")));
        datasource.setValidationQuery(env.getProperty("jdbc.validationQuery"));

        return datasource;
    }
}
