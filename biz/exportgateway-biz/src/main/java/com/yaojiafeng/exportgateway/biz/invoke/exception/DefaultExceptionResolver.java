package com.yaojiafeng.exportgateway.biz.invoke.exception;

import com.yaojiafeng.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.yaojiafeng.exportgateway.biz.invoke.response.ResponseContainer;
import com.yaojiafeng.exportgateway.biz.invoke.response.RpcInvokeResponse;
import com.yaojiafeng.exportgateway.biz.invoke.response.StateCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/28 上午10:18 $
 */
@Order(1)
@Component
public class DefaultExceptionResolver implements ExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionResolver.class);


    @Override
    public ResponseContainer doResolveException(RpcInvokeRequest request, RpcInvokeResponse response, Object handler, Exception ex) {
        ResponseContainer rc = new ResponseContainer();
        rc.setBody("");

        if (ex instanceof InvokeException) {
            rc.setStateCode(((InvokeException) ex).getStateCode());
        } else {
            rc.setStateCode(StateCodes.ABNORMAL_SYSTEM);
        }

        logger.error("invokeID:" + request.getEgProtocol().getInvokeID() + "," + ex.getMessage(), ex);

        return rc;
    }
}
