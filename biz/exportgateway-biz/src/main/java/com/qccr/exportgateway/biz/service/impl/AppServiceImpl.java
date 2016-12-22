/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.service.impl;

import com.qccr.exportgateway.biz.service.AppService;
import com.qccr.exportgateway.common.Page;
import com.qccr.exportgateway.dal.dao.AppDao;
import com.qccr.exportgateway.dal.entity.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/7 上午11:14 $
 */
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private AppDao appDao;


    @Override
    public List<App> findAppList(Byte status, String appName, Page page) {
        return appDao.findAppList(status, appName, page);
    }

    @Override
    public int saveApp(App app) {
        App app1 = appDao.findByAppId(app.getAppId());
        if (app.getId() == null) {
            if (app1 != null) {
                throw new RuntimeException("已经存在相同的配置,APPID不可重复");
            }
            return appDao.insertApp(app);
        } else {
            if (!app.getId().equals(app1.getId())) {
                throw new RuntimeException("已经存在相同的配置,APPID不可重复");
            }
            return appDao.updateApp(app);
        }
    }

    @Override
    public App findById(Integer id) {
        return appDao.findById(id);
    }

    @Override
    public int deleteById(Integer id) {
        return appDao.deleteById(id);
    }

    @Override
    public List<App> findAll() {
        return appDao.findAll();
    }
}
