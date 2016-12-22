/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.service;

import com.qccr.exportgateway.common.Page;
import com.qccr.exportgateway.dal.entity.AppMethod;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/31 上午11:33 $
 */
public interface AppMethodService {

    List<AppMethod> findAppMethodList(Byte status, Integer appId, Page page);

    int saveAppMethod(AppMethod appMethod);

    AppMethod findById(Integer id);

    int deleteById(Integer id);
}
