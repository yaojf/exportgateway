/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.invoke.request;

import com.qccr.exportgateway.biz.invoke.interceptor.InvokeInterceptor;
import com.qccr.exportgateway.biz.invoke.response.ResponseContainer;
import com.qccr.exportgateway.biz.invoke.response.RpcInvokeResponse;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午4:14 $
 */
public class InvokeExecutionChain {
    private static final Logger logger = LoggerFactory.getLogger(InvokeExecutionChain.class);

    /**
     * 处理器类
     */
    private final Object handler;

    /**
     * 调用拦截器
     */
    private List<InvokeInterceptor> interceptorList;

    /**
     * 记录执行到哪个拦截器
     */
    private int interceptorIndex = -1;

    public InvokeExecutionChain(Object handler, List<InvokeInterceptor> interceptorList) {
        this.handler = handler;
        this.interceptorList = interceptorList;
    }

    public Object getHandler() {
        return handler;
    }

    public List<InvokeInterceptor> getInterceptorList() {
        return interceptorList;
    }

    public void setInterceptorList(List<InvokeInterceptor> interceptorList) {
        this.interceptorList = interceptorList;
    }

    public boolean applyPreHandle(RpcInvokeRequest request, RpcInvokeResponse response) throws Exception {
        if (CollectionUtils.isNotEmpty(interceptorList)) {
            for (int i = 0; i < interceptorList.size(); i++) {
                InvokeInterceptor interceptor = interceptorList.get(i);
                if (!interceptor.preInvoke(request, response, this.handler)) {
                    triggerAfterCompletion(request, response, null);
                    return false;
                }
                this.interceptorIndex = i;
            }
        }
        return true;
    }


    public void applyPostHandle(RpcInvokeRequest request, RpcInvokeResponse response, ResponseContainer container) throws Exception {
        if (CollectionUtils.isNotEmpty(interceptorList)) {
            for (int i = interceptorList.size() - 1; i >= 0; i--) {
                InvokeInterceptor interceptor = interceptorList.get(i);
                interceptor.postInvoke(request, response, this.handler, container);
            }
        }
    }

    public void triggerAfterCompletion(RpcInvokeRequest request, RpcInvokeResponse response, Throwable throwable) {
        if (!CollectionUtils.isNotEmpty(interceptorList)) {
            for (int i = this.interceptorIndex; i >= 0; i--) {
                InvokeInterceptor interceptor = interceptorList.get(i);
                try {
                    interceptor.afterCompletion(request, response, this.handler, throwable);
                } catch (Throwable ex2) {
                    logger.error("InvokeInterceptor.afterCompletion threw exception", ex2);
                }
            }
        }
    }
}
