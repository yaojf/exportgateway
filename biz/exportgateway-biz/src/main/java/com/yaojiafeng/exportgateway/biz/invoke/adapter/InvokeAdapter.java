package com.yaojiafeng.exportgateway.biz.invoke.adapter;

import com.yaojiafeng.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.yaojiafeng.exportgateway.biz.invoke.response.ResponseContainer;
import com.yaojiafeng.exportgateway.biz.invoke.response.RpcInvokeResponse;

/**
 * 处理器适配器,负责执行操作
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午3:28 $
 */
public interface InvokeAdapter {

    boolean supports(Object handler);

    ResponseContainer invoke(RpcInvokeRequest request, RpcInvokeResponse response, Object handler) throws Exception;
}
