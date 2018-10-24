package com.cat.config;

import com.cat.component.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxiaoqiang
 * @since 2018/10/23
 **/
//@Configuration
public class WebConfig {
    /**
     * 配置自定义过滤器
     * @return
     */
//    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

}
