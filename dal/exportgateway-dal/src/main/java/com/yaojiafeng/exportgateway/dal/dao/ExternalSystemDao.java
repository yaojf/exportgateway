/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.yaojiafeng.exportgateway.dal.dao;

import com.yaojiafeng.exportgateway.common.Page;
import com.yaojiafeng.exportgateway.dal.entity.ExternalSystem;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/29 下午8:23 $
 */
public interface ExternalSystemDao {

    List<ExternalSystem> findAll();

    List<ExternalSystem> findExternalSystemList(Byte status, String enName, Page page);

    ExternalSystem findById(Integer id);

    int deleteById(Integer id);

    int insertExternalSystem(ExternalSystem externalSystem);

    int updateExternalSystem(ExternalSystem externalSystem);

    ExternalSystem findByEnName(String enName);
}
