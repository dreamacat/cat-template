package com.cat.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author wangxiaoqiang
 */

@Configuration
@AutoConfigureAfter({ DataSourceConfig.class })
@PropertySource("classpath:properties/mybatis.properties")
@MapperScan(basePackages = { "com.cat.mapper"})
@EnableTransactionManagement
@Slf4j
public class MybatisConfig implements TransactionManagementConfigurer {

    @Autowired
    private Environment env;

    @Resource(name = "dataSource")
    private DataSource dataSource;


    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() {
        try {
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            Properties properties = new Properties();
            properties.setProperty("dialect", env.getProperty("mybatis.dialect"));
            sessionFactory.setConfigurationProperties(properties);
            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));
            System.out.println(env.getProperty("mybatis.mapperLocations"));

            //Interceptor[] plugins = new Interceptor[]{new PaginationInterceptor()};
            //sessionFactory.setPlugins(plugins);
            return sessionFactory.getObject();
        } catch (Exception e) {
            log.error("error", e);
            return null;
        }
    }

    @Bean(name="sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name="transactionManager")
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
