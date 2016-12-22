/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.invoke.response;

import com.qccr.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.qccr.exportgateway.common.utils.DateUtils;
import com.qccr.exportgateway.facade.ogw.protocol.EGProtocol;

import java.util.Date;

/**
 * 返回值解析到response
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午5:25 $
 */
public class ResponseResolverUtils {

    public static void resolveResponse(ResponseContainer container, RpcInvokeRequest request, RpcInvokeResponse response) {
        response.setBody(container.getBody());
        response.setStateCode(container.getStateCode());

        EGProtocol egProtocol = request.getEgProtocol();
        egProtocol.setResponseTimestamp(DateUtils.format(new Date()));
        egProtocol.setCode(container.getStateCode().getCode());
        egProtocol.setMsg(container.getStateCode().getMsg());
        egProtocol.setSubCode(container.getStateCode().getSubCode());
        egProtocol.setSubMsg(container.getStateCode().getSubMsg());
        egProtocol.setBody(container.getBody());

        response.setEgProtocol(egProtocol);
    }
}
