/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.invoke.mapping;


import com.qccr.exportgateway.biz.invoke.request.InvokeExecutionChain;
import com.qccr.exportgateway.biz.invoke.request.RpcInvokeRequest;

/**
 * 查找处理方法类
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午3:31 $
 */
public interface InvokeMapping {
    /**
     * 通用调用
     */
    Byte INVOKE_TYPE_COMMON = 1;
    /**
     * 自定义调用
     */
    Byte INVOKE_TYPE_CUSTOM = 2;

    InvokeExecutionChain getHandler(RpcInvokeRequest request) throws Exception;

}
