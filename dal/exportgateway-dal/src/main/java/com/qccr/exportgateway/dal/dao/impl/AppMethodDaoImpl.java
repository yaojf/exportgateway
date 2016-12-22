/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.dal.dao.impl;

import com.qccr.exportgateway.common.Constants;
import com.qccr.exportgateway.common.Page;
import com.qccr.exportgateway.dal.dao.AppMethodDao;
import com.qccr.exportgateway.dal.entity.AppMethod;
import com.qccr.exportgateway.dal.mapper.AppMethodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/29 下午8:54 $
 */
@Repository
public class AppMethodDaoImpl implements AppMethodDao {

    @Autowired
    private AppMethodMapper mapper;


    @Override
    public List<AppMethod> findAll() {
        return mapper.selectAll();
    }

    @Override
    public List<AppMethod> findAppMethodList(Byte status, Integer appId, Page page) {
        if (page != null) {
            int num = mapper.getAppMethodListNum(status, appId);
            page.setTotalNum(num);
        }
        return mapper.getAppMethodList(status, appId, page);
    }

    @Override
    public AppMethod findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertAppMethod(AppMethod appMethod) {
        Date date = new Date();
        appMethod.setCreateTime(date);
        appMethod.setUpdateTime(date);
        appMethod.setCreatePerson(Constants.SYSTEM_PERSON);
        appMethod.setUpdatePerson(Constants.SYSTEM_PERSON);
        return mapper.insertSelective(appMethod);
    }

    @Override
    public int updateAppMethod(AppMethod appMethod) {
        Date date = new Date();
        appMethod.setUpdateTime(date);
        appMethod.setUpdatePerson(Constants.SYSTEM_PERSON);
        return mapper.updateByPrimaryKeySelective(appMethod);
    }

    @Override
    public AppMethod findByAppIdAndMethodId(Integer appId, Integer methodId) {
        return mapper.findByAppIdAndMethodId(appId, methodId);
    }
}
