/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.facade.ogw.impl;

import com.qccr.exportgateway.biz.invoke.DispatcherInvoker;
import com.qccr.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.qccr.exportgateway.biz.invoke.response.RpcInvokeResponse;
import com.qccr.exportgateway.common.utils.LogUtils;
import com.qccr.exportgateway.facade.ogw.ExportGatewayFacade;
import com.qccr.exportgateway.facade.ogw.protocol.EGProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/6 下午6:40 $
 */
public class ExportGatewayFacadeImpl implements ExportGatewayFacade {
    private static final Logger logger = LoggerFactory.getLogger(ExportGatewayFacadeImpl.class);

    @Autowired
    private DispatcherInvoker dispatcherInvoker;

    @Override
    public EGProtocol invoke(EGProtocol egProtocol) {
        LogUtils.info(logger, "接收到请求,请求参数为:[{}]", egProtocol.toString());
        long start = System.currentTimeMillis();
        EGProtocol responseEgProtocol = null;
        try {
            RpcInvokeRequest request = new RpcInvokeRequest();
            request.setEgProtocol(egProtocol);
            RpcInvokeResponse response = new RpcInvokeResponse();
            dispatcherInvoker.doInvoke(request, response);
            responseEgProtocol = response.getEgProtocol();
        } finally {
            LogUtils.info(logger, "请求处理结束,处理时间为:[{}ms],返回参数为:[{}]", System.currentTimeMillis() - start, responseEgProtocol.toString());
        }

        return responseEgProtocol;
    }


}
