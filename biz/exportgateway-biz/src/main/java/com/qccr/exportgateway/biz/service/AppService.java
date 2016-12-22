/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.service;

import com.qccr.exportgateway.common.Page;
import com.qccr.exportgateway.dal.entity.App;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/7 上午11:13 $
 */
public interface AppService {

    List<App> findAppList(Byte status, String appName, Page page);

    int saveApp(App app);

    App findById(Integer id);

    int deleteById(Integer id);

    List<App> findAll();
}
