/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.dal.dao;

import com.qccr.exportgateway.dal.entity.App;
import com.qccr.exportgateway.common.Page;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/6 下午11:11 $
 */
public interface AppDao {
    List<App> findAppList(Byte status, String appName, Page page);

    int insertApp(App app);

    App findById(Integer id);

    int updateApp(App app);

    int deleteById(Integer id);

    List<App> findAll();

    App findByAppId(String appId);
}
