/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.dal.dao.impl;

import com.qccr.exportgateway.common.Constants;
import com.qccr.exportgateway.common.Page;
import com.qccr.exportgateway.dal.dao.AppDao;
import com.qccr.exportgateway.dal.entity.App;
import com.qccr.exportgateway.dal.mapper.AppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/6 下午11:11 $
 */
@Repository
public class AppDaoImpl implements AppDao {
    @Autowired
    private AppMapper appMapper;

    @Override
    public List<App> findAppList(Byte status, String appName, Page page) {
        if (page != null) {
            int num = appMapper.getAppListNum(status, appName);
            page.setTotalNum(num);
        }
        return appMapper.getAppList(status, appName, page);
    }

    @Override
    public int insertApp(App app) {
        Date date = new Date();
        app.setCreateTime(date);
        app.setUpdateTime(date);
        app.setCreatePerson(Constants.SYSTEM_PERSON);
        app.setUpdatePerson(Constants.SYSTEM_PERSON);
        return appMapper.insertSelective(app);
    }

    @Override
    public App findById(Integer id) {
        return appMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateApp(App app) {
        Date date = new Date();
        app.setUpdateTime(date);
        app.setUpdatePerson(Constants.SYSTEM_PERSON);
        return appMapper.updateByPrimaryKeySelective(app);
    }

    @Override
    public int deleteById(Integer id) {
        return appMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<App> findAll() {
        return appMapper.selectAll();
    }

    @Override
    public App findByAppId(String appId) {
        return appMapper.findByAppId(appId);
    }
}
