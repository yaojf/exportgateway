package com.yaojiafeng.exportgateway.biz.invoke.annotation;


import java.lang.annotation.*;

/**
 * 对应唯一一个外部接口
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午3:17 $
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Method {
    String value() default "";

    String systemName() default "";
}
