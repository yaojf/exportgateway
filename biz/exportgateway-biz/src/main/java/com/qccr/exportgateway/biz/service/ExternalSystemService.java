/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.service;

import com.qccr.exportgateway.common.Page;
import com.qccr.exportgateway.dal.entity.ExternalSystem;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/31 上午11:33 $
 */
public interface ExternalSystemService {

    List<ExternalSystem> findExternalSystemList(Byte status, String enName, Page page);

    int saveExternalSystem(ExternalSystem externalSystem);

    ExternalSystem findById(Integer id);

    int deleteById(Integer id);

    List<ExternalSystem> findAll();

}
