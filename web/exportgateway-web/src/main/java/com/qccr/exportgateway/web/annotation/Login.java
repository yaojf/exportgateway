/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.web.annotation;

import java.lang.annotation.*;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/8/5 下午2:52 $
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Login {
    boolean login() default true;
}
