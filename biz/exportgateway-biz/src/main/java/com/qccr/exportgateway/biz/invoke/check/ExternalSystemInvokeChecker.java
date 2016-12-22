/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.invoke.check;

import com.google.common.collect.Maps;
import com.qccr.exportgateway.biz.invoke.exception.InvokeException;
import com.qccr.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.qccr.exportgateway.biz.invoke.response.RpcInvokeResponse;
import com.qccr.exportgateway.biz.invoke.response.StateCodes;
import com.qccr.exportgateway.dal.dao.ExternalSystemDao;
import com.qccr.exportgateway.dal.entity.App;
import com.qccr.exportgateway.dal.entity.Constants;
import com.qccr.exportgateway.dal.entity.ExternalSystem;
import com.qccr.exportgateway.facade.ogw.protocol.EGProtocol;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/28 下午7:29 $
 */
@Component
@Order(2)
public class ExternalSystemInvokeChecker extends AbstractInvokeChecker {

    @Autowired
    private ExternalSystemDao externalSystemDao;

    private volatile Map<String, ExternalSystem> externalSystemLookupMap;

    @Override
    public void doCheck(RpcInvokeRequest request, RpcInvokeResponse response) {
        EGProtocol egProtocol = request.getEgProtocol();
        if (StringUtils.isBlank(egProtocol.getExternalSystemName())) {
            throw new InvokeException(StateCodes.THE_EXTERNAL_SYSTEM_DOES_NOT_EXIST);
        }

        ExternalSystem externalSystem = externalSystemLookupMap.get(egProtocol.getExternalSystemName());
        if (externalSystem == null) {
            throw new InvokeException(StateCodes.THE_EXTERNAL_SYSTEM_DOES_NOT_EXIST);
        }

        if (!externalSystem.getStatus().equals(Constants.VALID_STATUS)) {
            throw new InvokeException(StateCodes.THE_CURRENT_EXTERNAL_SYSTEM_IS_NOT_AVAILABLE);
        }

        request.setExternalSystem(externalSystem);
    }

    @Override
    public synchronized void init() {
        Map<String, ExternalSystem> externalSystemMap = Maps.newHashMap();
        List<ExternalSystem> externalSystemList = externalSystemDao.findAll();
        if (CollectionUtils.isNotEmpty(externalSystemList)) {
            for (ExternalSystem externalSystem : externalSystemList) {
                externalSystemMap.put(externalSystem.getEnName(), externalSystem);
            }
        }
        this.externalSystemLookupMap = externalSystemMap;
    }


}
