//package com.cat.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author wangxiaoqiang
// * @since 2018/09/03
// **/
//
////@EnableWebSecurity
////@EnableGlobalMethodSecurity(prePostEnabled = true)
//@PropertySource("classpath:properties/security.properties")
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private Environment env;
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/*").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser(env.getProperty("user.uname"))
//                .password(env.getProperty("user.pwd")).roles("USER");
//    }
//
//
////    @Override
////    public void configure(WebSecurity web) throws Exception {
////        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico"); //静态资源地址
////    }
//
////    @Override
////    public void configure(AuthenticationManagerBuilder auth) throws Exception {
////        System.out.println(env.getProperty("user.uname"));
////        System.out.println(env.getProperty("user.pwd"));
////        auth.inMemoryAuthentication()
////                .withUser(env.getProperty("user.uname")).password(env.getProperty("user.pwd"));
//////                .roles("USER","USER2");    //配置的用户信息地址
////
////    }
//
//
//}
