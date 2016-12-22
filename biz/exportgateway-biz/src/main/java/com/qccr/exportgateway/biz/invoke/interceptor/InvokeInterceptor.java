/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.invoke.interceptor;

import com.qccr.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.qccr.exportgateway.biz.invoke.response.ResponseContainer;
import com.qccr.exportgateway.biz.invoke.response.RpcInvokeResponse;

/**
 * 具体调用请求拦截器
 *
 * 预留,目前没有实现类
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午4:00 $
 */
public interface InvokeInterceptor {

    boolean preInvoke(RpcInvokeRequest request, RpcInvokeResponse response, Object handler)
            throws Exception;


    void postInvoke(RpcInvokeRequest request, RpcInvokeResponse response, Object handler, ResponseContainer rc)
            throws Exception;

    void afterCompletion(RpcInvokeRequest request, RpcInvokeResponse response, Object handler, Throwable throwable);
}
