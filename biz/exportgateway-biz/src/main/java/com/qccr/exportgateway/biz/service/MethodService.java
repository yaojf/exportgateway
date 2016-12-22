/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.service;

import com.qccr.exportgateway.common.Page;
import com.qccr.exportgateway.dal.entity.Method;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/31 上午11:34 $
 */
public interface MethodService {
    int deleteById(Integer id);

    Method findById(Integer id);

    int saveMethod(Method method);

    List<Method> findMethodList(Byte status, String name, Integer systemId, Page page);

    List<Method> findAll();
}
