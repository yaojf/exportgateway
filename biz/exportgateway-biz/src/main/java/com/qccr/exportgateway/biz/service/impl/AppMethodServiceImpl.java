/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.service.impl;

import com.qccr.exportgateway.biz.service.AppMethodService;
import com.qccr.exportgateway.common.Page;
import com.qccr.exportgateway.dal.dao.AppMethodDao;
import com.qccr.exportgateway.dal.entity.AppMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/31 上午11:34 $
 */
@Service
public class AppMethodServiceImpl implements AppMethodService {

    @Autowired
    private AppMethodDao appMethodDao;


    @Override
    public List<AppMethod> findAppMethodList(Byte status, Integer appId, Page page) {
        return appMethodDao.findAppMethodList(status, appId, page);
    }

    @Override
    public int saveAppMethod(AppMethod appMethod) {
        //appId和methodId为联合主键
        AppMethod appMethod1 = appMethodDao.findByAppIdAndMethodId(appMethod.getAppId(), appMethod.getMethodId());

        if (appMethod.getId() == null) {
            if (appMethod1 != null) {
                throw new RuntimeException("已经存在相同的授权配置");
            }
            return appMethodDao.insertAppMethod(appMethod);
        } else {
            if (!appMethod.getId().equals(appMethod1.getId())) {
                throw new RuntimeException("已经存在相同的授权配置");
            }
            return appMethodDao.updateAppMethod(appMethod);
        }
    }

    @Override
    public AppMethod findById(Integer id) {
        return appMethodDao.findById(id);
    }

    @Override
    public int deleteById(Integer id) {
        return appMethodDao.deleteById(id);
    }
}
