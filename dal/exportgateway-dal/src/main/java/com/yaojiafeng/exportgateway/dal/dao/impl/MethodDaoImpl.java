/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.yaojiafeng.exportgateway.dal.dao.impl;

import com.yaojiafeng.exportgateway.common.Constants;
import com.yaojiafeng.exportgateway.common.Page;
import com.yaojiafeng.exportgateway.dal.dao.MethodDao;
import com.yaojiafeng.exportgateway.dal.entity.Method;
import com.yaojiafeng.exportgateway.dal.mapper.MethodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/29 下午8:35 $
 */
@Repository
public class MethodDaoImpl implements MethodDao {

    @Autowired
    private MethodMapper mapper;


    @Override
    public List<Method> findAll() {
        return mapper.selectAll();
    }

    @Override
    public int insertMethod(Method method) {
        Date date = new Date();
        method.setCreateTime(date);
        method.setUpdateTime(date);
        method.setCreatePerson(Constants.SYSTEM_PERSON);
        method.setUpdatePerson(Constants.SYSTEM_PERSON);
        return mapper.insertSelective(method);
    }

    @Override
    public int updateMethod(Method method) {
        Date date = new Date();
        method.setUpdateTime(date);
        method.setUpdatePerson(Constants.SYSTEM_PERSON);
        return mapper.updateByPrimaryKeySelective(method);
    }

    @Override
    public Method findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Method> findMethodList(Byte status, String name, Integer systemId, Page page) {
        if (page != null) {
            int num = mapper.getMethodListNum(status, name, systemId);
            page.setTotalNum(num);
        }
        return mapper.getMethodList(status, name, systemId, page);
    }
}
