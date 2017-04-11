package com.yaojiafeng.exportgateway.biz.invoke;

import com.yaojiafeng.exportgateway.biz.invoke.adapter.InvokeAdapter;
import com.yaojiafeng.exportgateway.biz.invoke.check.InvokeChecker;
import com.yaojiafeng.exportgateway.biz.invoke.exception.ExceptionResolver;
import com.yaojiafeng.exportgateway.biz.invoke.exception.InvokeException;
import com.yaojiafeng.exportgateway.biz.invoke.mapping.InvokeMapping;
import com.yaojiafeng.exportgateway.biz.invoke.request.InvokeExecutionChain;
import com.yaojiafeng.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.yaojiafeng.exportgateway.biz.invoke.response.ResponseContainer;
import com.yaojiafeng.exportgateway.biz.invoke.response.ResponseResolverUtils;
import com.yaojiafeng.exportgateway.biz.invoke.response.RpcInvokeResponse;
import com.yaojiafeng.exportgateway.biz.invoke.response.StateCodes;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * rpc请求分发器
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午3:50 $
 */
@Component
public class DispatcherInvoker implements ApplicationContextAware, InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherInvoker.class);

    /**
     * 映射查找器
     */
    private List<InvokeMapping> invokeMappings;
    /**
     * 处理适配器
     */
    private List<InvokeAdapter> invokeAdapters;
    /**
     * 异常解析器
     */
    private List<ExceptionResolver> exceptionResolvers;
    /**
     * 前置校验器
     */
    private List<InvokeChecker> invokeCheckers;

    private ApplicationContext applicationContext;

    /**
     * 初始化各个组件
     * @param context
     */
    protected void initStrategies(ApplicationContext context) {
        initInvokeMappings(context);
        initInvokeAdapters(context);
        initExceptionResolvers(context);
        initInvokeCheckers(context);
    }

    private void initInvokeCheckers(ApplicationContext context) {
        this.invokeCheckers = null;

        Map<String, InvokeChecker> matchingBeans =
                BeanFactoryUtils.beansOfTypeIncludingAncestors(context, InvokeChecker.class, true, false);
        if (!matchingBeans.isEmpty()) {
            this.invokeCheckers = new ArrayList<InvokeChecker>(matchingBeans.values());
            AnnotationAwareOrderComparator.sort(this.invokeCheckers);
        }
    }

    private void initExceptionResolvers(ApplicationContext context) {
        this.exceptionResolvers = null;

        Map<String, ExceptionResolver> matchingBeans =
                BeanFactoryUtils.beansOfTypeIncludingAncestors(context, ExceptionResolver.class, true, false);
        if (!matchingBeans.isEmpty()) {
            this.exceptionResolvers = new ArrayList<ExceptionResolver>(matchingBeans.values());
            AnnotationAwareOrderComparator.sort(this.exceptionResolvers);
        }
    }

    private void initInvokeAdapters(ApplicationContext context) {
        this.invokeAdapters = null;

        Map<String, InvokeAdapter> matchingBeans =
                BeanFactoryUtils.beansOfTypeIncludingAncestors(context, InvokeAdapter.class, true, false);
        if (!matchingBeans.isEmpty()) {
            this.invokeAdapters = new ArrayList<InvokeAdapter>(matchingBeans.values());
            AnnotationAwareOrderComparator.sort(this.invokeAdapters);
        }
    }

    private void initInvokeMappings(ApplicationContext context) {
        this.invokeMappings = null;

        Map<String, InvokeMapping> matchingBeans =
                BeanFactoryUtils.beansOfTypeIncludingAncestors(context, InvokeMapping.class, true, false);

        if (!matchingBeans.isEmpty()) {
            this.invokeMappings = new ArrayList<InvokeMapping>(matchingBeans.values());
            AnnotationAwareOrderComparator.sort(this.invokeMappings);
        }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initStrategies(applicationContext);
    }


    /**
     * 请求入口
     * @param request
     * @param response
     */
    public void doInvoke(RpcInvokeRequest request, RpcInvokeResponse response) {
        InvokeExecutionChain invokeExecutionChain = null;

        try {
            Exception dispatchException = null;
            ResponseContainer container = null;
            try {
                //前置校验配置信息校验
                doCheck(request, response);

                //查找请求执行链
                invokeExecutionChain = getInvokeHandler(request);

                if (invokeExecutionChain == null || invokeExecutionChain.getHandler() == null) {
                    noInvokeHandlerFound(request, response);
                    return;
                }
                //根据请求处理器对象,获取具体的处理器适配器
                InvokeAdapter invokeAdapter = getInvokeAdapter(invokeExecutionChain.getHandler());
                //前置拦截器处理
                if (!invokeExecutionChain.applyPreHandle(request, response)) {
                    return;
                }
                //具体的处理器适配器调用
                container = invokeAdapter.invoke(request, response, invokeExecutionChain.getHandler());
                //后置拦截器处理
                invokeExecutionChain.applyPostHandle(request, response, container);
            } catch (Exception ex) {
                dispatchException = ex;
            }
            //处理返回结果
            processDispatchResult(request, response, invokeExecutionChain, container, dispatchException);
        } catch (Throwable throwable) {
            //最终拦截器处理
            triggerAfterCompletion(request, response, invokeExecutionChain, throwable);
        }

    }


    protected void doCheck(RpcInvokeRequest request, RpcInvokeResponse response) {
        if (CollectionUtils.isNotEmpty(invokeCheckers)) {
            for (InvokeChecker ic : invokeCheckers) {
                ic.doCheck(request, response);
            }
        }
    }

    protected InvokeAdapter getInvokeAdapter(Object handler) {
        for (InvokeAdapter ia : this.invokeAdapters) {
            if (ia.supports(handler)) {
                return ia;
            }
        }
        //找不到,说明代码没写,直接抛系统异常
        throw new InvokeException(StateCodes.ABNORMAL_SYSTEM);
    }

    protected InvokeExecutionChain getInvokeHandler(RpcInvokeRequest request) throws Exception {
        for (InvokeMapping im : this.invokeMappings) {
            InvokeExecutionChain handler = im.getHandler(request);
            if (handler != null) {
                return handler;
            }
        }
        return null;
    }

    protected void noInvokeHandlerFound(RpcInvokeRequest request, RpcInvokeResponse response) {
        //目前,找不到直接抛异常
        throw new InvokeException(StateCodes.ABNORMAL_SYSTEM);
    }

    protected void processDispatchResult(RpcInvokeRequest request, RpcInvokeResponse response, InvokeExecutionChain invokeExecutionChain, ResponseContainer container, Exception exception) {
        //处理异常
        if (exception != null) {
            Object handler = (invokeExecutionChain != null ? invokeExecutionChain.getHandler() : null);
            container = processInvokeException(request, response, handler, exception);
        }
        //渲染返回值
        if (container != null) {
            render(container, request, response);
        }

        if (invokeExecutionChain != null) {
            invokeExecutionChain.triggerAfterCompletion(request, response, null);
        }

    }

    private void render(ResponseContainer container, RpcInvokeRequest request, RpcInvokeResponse response) {
        ResponseResolverUtils.resolveResponse(container, request, response);
    }

    private ResponseContainer processInvokeException(RpcInvokeRequest request, RpcInvokeResponse response, Object handler, Exception exception) {
        ResponseContainer rc = null;
        for (ExceptionResolver exceptionResolver : this.exceptionResolvers) {
            rc = exceptionResolver.doResolveException(request, response, handler, exception);
            if (rc != null) {
                break;
            }
        }

        return rc;
    }

    private void triggerAfterCompletion(RpcInvokeRequest request, RpcInvokeResponse response, InvokeExecutionChain invokeExecutionChain, Throwable throwable) {
        if (invokeExecutionChain != null) {
            invokeExecutionChain.triggerAfterCompletion(request, response, throwable);
        }
    }

}
