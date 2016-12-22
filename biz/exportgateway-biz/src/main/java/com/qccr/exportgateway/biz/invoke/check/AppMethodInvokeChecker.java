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
import com.qccr.exportgateway.dal.dao.AppMethodDao;
import com.qccr.exportgateway.dal.entity.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/28 下午7:28 $
 */
@Component
@Order(4)
public class AppMethodInvokeChecker extends AbstractInvokeChecker {
    private static final String SEPERATOR = "^";

    @Autowired
    private AppMethodDao appMethodDao;

    private volatile Map<String, AppMethod> appMethodLookupMap;

    @Override
    public void doCheck(RpcInvokeRequest request, RpcInvokeResponse response) {
        App app = request.getApp();
        Method method = request.getMethod();

        AppMethod appMethod = appMethodLookupMap.get(app.getId() + SEPERATOR + method.getId());
        if (method == null || (!method.getStatus().equals(Constants.VALID_STATUS))) {
            throw new InvokeException(StateCodes.NO_RIGHT_TO_CALL_THIS_INTERFACE);
        }

        request.setAppMethod(appMethod);
    }

    @Override
    public synchronized void init() {
        Map<String, AppMethod> appMethodMap = Maps.newHashMap();
        List<AppMethod> appMethodList = appMethodDao.findAll();
        if (CollectionUtils.isNotEmpty(appMethodList)) {
            for (AppMethod appMethod : appMethodList) {
                StringBuilder sb = new StringBuilder();
                sb.append(appMethod.getAppId()).append(SEPERATOR).append(appMethod.getMethodId());
                appMethodMap.put(sb.toString(), appMethod);
            }
        }
        this.appMethodLookupMap = appMethodMap;
    }
}
