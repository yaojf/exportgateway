/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.invoke.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义方法的配置注解
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午3:19 $
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface InvokeController {
    String value() default "";
}
