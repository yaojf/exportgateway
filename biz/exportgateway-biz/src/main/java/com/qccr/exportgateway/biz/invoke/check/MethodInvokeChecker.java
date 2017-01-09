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
import com.qccr.exportgateway.dal.dao.MethodDao;
import com.qccr.exportgateway.dal.entity.Constants;
import com.qccr.exportgateway.dal.entity.Method;
import com.qccr.exportgateway.facade.ogw.protocol.EGProtocol;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/28 下午7:30 $
 */
@Component
@Order(3)
public class MethodInvokeChecker extends AbstractInvokeChecker {

    @Autowired
    private MethodDao methodDao;

    private volatile Map<String, Method> methodLookupMap;

    @Override
    public void doCheck(RpcInvokeRequest request, RpcInvokeResponse response) {
        EGProtocol egProtocol = request.getEgProtocol();
        if (StringUtils.isBlank(egProtocol.getMethod())) {
            throw new InvokeException(StateCodes.NON_EXISTING_EXTERNAL_SERVICES);
        }

        Method method = methodLookupMap.get(egProtocol.getMethod());
        if (method == null) {
            throw new InvokeException(StateCodes.NON_EXISTING_EXTERNAL_SERVICES);
        }

        if (!method.getStatus().equals(Constants.VALID_STATUS)) {
            throw new InvokeException(StateCodes.THE_CURRENT_EXTERNAL_SERVICE_IS_NOT_AVAILABLE);
        }

        request.setMethod(method);
    }

    @Override
    public synchronized void init() {
        Map<String, Method> methodMap = Maps.newHashMap();
        List<Method> methodList = methodDao.findAll();
        if (CollectionUtils.isNotEmpty(methodList)) {
            for (Method method : methodList) {
                methodMap.put(method.getName(), method);
            }
        }
        this.methodLookupMap = methodMap;
    }


}
