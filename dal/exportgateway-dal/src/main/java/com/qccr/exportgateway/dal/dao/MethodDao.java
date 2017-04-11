/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.dal.dao;

import com.yaojiafeng.exportgateway.common.Page;
import com.qccr.exportgateway.dal.entity.Method;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/29 下午8:35 $
 */
public interface MethodDao {

    List<Method> findAll();

    int insertMethod(Method method);

    int updateMethod(Method method);

    Method findById(Integer id);

    int deleteById(Integer id);

    List<Method> findMethodList(Byte status, String name, Integer systemId, Page page);
}
