package com.cat.annotations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
public @interface View {
    /**
     * 设置一个名字,防止不同包下, 同样类的重名的问题
     */
    public String value() default "";
    
    /**
     * swagger的文档说明
     * @return
     */
    public String desc() default "";
}
