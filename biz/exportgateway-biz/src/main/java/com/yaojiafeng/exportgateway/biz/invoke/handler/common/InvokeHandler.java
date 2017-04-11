package com.yaojiafeng.exportgateway.biz.invoke.handler.common;

import com.yaojiafeng.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.yaojiafeng.exportgateway.biz.invoke.response.RpcInvokeResponse;

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
