package com.yaojiafeng.exportgateway.biz.invoke.adapter;

import com.yaojiafeng.exportgateway.biz.invoke.mapping.InvokeMethod;
import com.yaojiafeng.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.yaojiafeng.exportgateway.biz.invoke.response.ResponseContainer;
import com.yaojiafeng.exportgateway.biz.invoke.response.RpcInvokeResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午4:29 $
 */
@Order(2)
@Component
public class CustomInvokeAdapter extends AbstractInvokeAdapter {


    @Override
    public boolean supports(Object handler) {
        return handler instanceof InvokeMethod;
    }

    @Override
    public ResponseContainer invoke(RpcInvokeRequest request, RpcInvokeResponse response, Object handler) throws Exception {
        ResponseContainer rc = new ResponseContainer();
        InvokeMethod im = (InvokeMethod) handler;
        im.doInvoke(request, response, rc);
        return rc;
    }

}
