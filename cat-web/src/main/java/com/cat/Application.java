package com.cat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ServletComponentScan
@EnableAspectJAutoProxy(exposeProxy = true) // 使用cglib方式实现代理
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}