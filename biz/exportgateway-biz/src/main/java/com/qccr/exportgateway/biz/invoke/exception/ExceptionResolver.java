/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.invoke.exception;

import com.qccr.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.qccr.exportgateway.biz.invoke.response.ResponseContainer;
import com.qccr.exportgateway.biz.invoke.response.RpcInvokeResponse;
import org.springframework.stereotype.Component;

/**
 * 异常处理类
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午4:47 $
 */
public interface ExceptionResolver {

    ResponseContainer doResolveException(RpcInvokeRequest request, RpcInvokeResponse response, Object handler, Exception ex);
}
