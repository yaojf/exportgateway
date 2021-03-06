package com.yaojiafeng.exportgateway.biz.invoke.mapping;

import com.yaojiafeng.exportgateway.biz.invoke.handler.common.InvokeHandler;
import com.yaojiafeng.exportgateway.biz.invoke.request.InvokeExecutionChain;
import com.yaojiafeng.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.yaojiafeng.exportgateway.dal.entity.Method;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午3:38 $
 */
@Order(1)
@Component
public class CommonInvokeMapping extends AbstractInvokeMapping {

    /**
     * 目前通用就一个
     */
    private InvokeHandler invokeHandler;

    @Override
    public InvokeExecutionChain getHandler(RpcInvokeRequest request) throws Exception {
        Method method = request.getMethod();
        if (method.getInvokeType().equals(INVOKE_TYPE_COMMON)) {
            InvokeExecutionChain invokeExecutionChain = new InvokeExecutionChain(invokeHandler, getInvokeInterceptors());
            return invokeExecutionChain;
        }

        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        InvokeHandler invokeHandler = BeanFactoryUtils.beanOfType(getApplicationContext(), InvokeHandler.class, true, false);
        if (invokeHandler != null) {
            this.invokeHandler = invokeHandler;
        }
    }

}
