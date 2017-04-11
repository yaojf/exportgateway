package com.yaojiafeng.exportgateway.biz.invoke.exception;

import com.yaojiafeng.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.yaojiafeng.exportgateway.biz.invoke.response.ResponseContainer;
import com.yaojiafeng.exportgateway.biz.invoke.response.RpcInvokeResponse;

/**
 * 异常处理类
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午4:47 $
 */
public interface ExceptionResolver {

    ResponseContainer doResolveException(RpcInvokeRequest request, RpcInvokeResponse response, Object handler, Exception ex);
}
