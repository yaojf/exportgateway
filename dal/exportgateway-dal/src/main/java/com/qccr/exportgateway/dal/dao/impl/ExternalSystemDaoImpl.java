/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.dal.dao.impl;

import com.yaojiafeng.exportgateway.common.Constants;
import com.yaojiafeng.exportgateway.common.Page;
import com.qccr.exportgateway.dal.dao.ExternalSystemDao;
import com.qccr.exportgateway.dal.entity.ExternalSystem;
import com.qccr.exportgateway.dal.mapper.ExternalSystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/29 下午8:23 $
 */
@Repository
public class ExternalSystemDaoImpl implements ExternalSystemDao {

    @Autowired
    private ExternalSystemMapper mapper;


    @Override
    public List<ExternalSystem> findAll() {
        return mapper.selectAll();
    }

    @Override
    public List<ExternalSystem> findExternalSystemList(Byte status, String enName, Page page) {
        if (page != null) {
            int num = mapper.getExternalSystemListNum(status, enName);
            page.setTotalNum(num);
        }
        return mapper.getExternalSystemList(status, enName, page);
    }

    @Override
    public ExternalSystem findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertExternalSystem(ExternalSystem externalSystem) {
        Date date = new Date();
        externalSystem.setCreateTime(date);
        externalSystem.setUpdateTime(date);
        externalSystem.setCreatePerson(Constants.SYSTEM_PERSON);
        externalSystem.setUpdatePerson(Constants.SYSTEM_PERSON);
        return mapper.insertSelective(externalSystem);
    }

    @Override
    public int updateExternalSystem(ExternalSystem externalSystem) {
        Date date = new Date();
        externalSystem.setUpdateTime(date);
        externalSystem.setUpdatePerson(Constants.SYSTEM_PERSON);
        return mapper.updateByPrimaryKeySelective(externalSystem);
    }

    @Override
    public ExternalSystem findByEnName(String enName) {
        return mapper.findByEnName(enName);
    }

}
