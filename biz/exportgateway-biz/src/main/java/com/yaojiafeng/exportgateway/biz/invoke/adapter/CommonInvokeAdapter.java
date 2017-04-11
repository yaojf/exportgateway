package com.yaojiafeng.exportgateway.biz.invoke.adapter;

import com.yaojiafeng.exportgateway.biz.invoke.handler.common.InvokeHandler;
import com.yaojiafeng.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.yaojiafeng.exportgateway.biz.invoke.response.ResponseContainer;
import com.yaojiafeng.exportgateway.biz.invoke.response.RpcInvokeResponse;
import com.yaojiafeng.exportgateway.biz.invoke.response.StateCodes;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午4:29 $
 */
@Order(1)
@Component
public class CommonInvokeAdapter extends AbstractInvokeAdapter {


    @Override
    public boolean supports(Object handler) {
        return handler instanceof InvokeHandler;
    }

    @Override
    public ResponseContainer invoke(RpcInvokeRequest request, RpcInvokeResponse response, Object handler) throws Exception {
        String body = ((InvokeHandler) handler).doInvoke(request, response);
        ResponseContainer rc = new ResponseContainer();
        rc.setBody(body);
        rc.setStateCode(StateCodes.SUCCESS);

        return rc;
    }

}
