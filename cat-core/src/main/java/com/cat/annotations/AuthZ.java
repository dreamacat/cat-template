package com.cat.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangxiaoqiang
 * @since 2018/10/26.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthZ {
    String value() default "";

    boolean logined() default false;

    String appName() default "";

    boolean exculde() default false;

    String roleAnd() default "";

    String roleOr() default "";

    String role() default "";

    String resAnd() default "";

    String resOr() default "";

    String res() default "";

    String errMsg() default "您没有当前的操作权限,请联系管理员或者尝试重新登录";

}
