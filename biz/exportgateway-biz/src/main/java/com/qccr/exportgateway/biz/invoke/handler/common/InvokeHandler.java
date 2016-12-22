/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.invoke.handler.common;

import com.qccr.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.qccr.exportgateway.biz.invoke.response.RpcInvokeResponse;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午3:30 $
 */
public interface InvokeHandler {
    String MD5 = "md5";
    String RSA = "rsa";
    String SIGN = "sign";
    String BODY = "body";

    String doInvoke(RpcInvokeRequest request, RpcInvokeResponse response) throws Exception;
}
