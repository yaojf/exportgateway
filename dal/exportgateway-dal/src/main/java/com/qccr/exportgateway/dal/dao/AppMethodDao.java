/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.dal.dao;

import com.yaojiafeng.exportgateway.common.Page;
import com.qccr.exportgateway.dal.entity.AppMethod;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/29 下午8:54 $
 */
public interface AppMethodDao {

    List<AppMethod> findAll();

    List<AppMethod> findAppMethodList(Byte status, Integer appId, Page page);

    AppMethod findById(Integer id);

    int deleteById(Integer id);

    int insertAppMethod(AppMethod appMethod);

    int updateAppMethod(AppMethod appMethod);

    AppMethod findByAppIdAndMethodId(Integer appId, Integer methodId);
}
