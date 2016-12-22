/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.invoke.annotation;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;


/**
 * 获取所有包含指定类注解的bean
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/29 上午11:44 $
 */
public class AnnotaionBeanFinder {

    public static List<String> findAnnotatedBeans(ApplicationContext applicationContext) {
        List<String> beans = new ArrayList<String>();
        for (String name : BeanFactoryUtils.beanNamesForTypeIncludingAncestors(applicationContext, Object.class)) {
            if (applicationContext.findAnnotationOnBean(name, InvokeController.class) != null) {
                beans.add(name);
            }
        }
        return beans;
    }
}
