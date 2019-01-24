package com.cat.config;

import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author wangxiaoqiang
 * @since 2018/10/30
 **/
@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {

    @Resource
    DataSource activitiDataSource;//注入配置好的数据源

    @Resource
    PlatformTransactionManager activitiTransactionManager;//注入配置好的事物管理器

    //注入数据源和事务管理器
    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
            SpringAsyncExecutor springAsyncExecutor) throws IOException {
        return this.baseSpringProcessEngineConfiguration(activitiDataSource, activitiTransactionManager, springAsyncExecutor);
    }
}

