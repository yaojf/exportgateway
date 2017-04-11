package com.yaojiafeng.exportgateway.biz.invoke.mapping;

import com.yaojiafeng.exportgateway.biz.invoke.interceptor.InvokeInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午3:35 $
 */
public abstract class AbstractInvokeMapping implements InvokeMapping, ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private List<InvokeInterceptor> invokeInterceptors;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public List<InvokeInterceptor> getInvokeInterceptors() {
        return invokeInterceptors;
    }

    public void setInvokeInterceptors(List<InvokeInterceptor> invokeInterceptors) {
        this.invokeInterceptors = invokeInterceptors;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initInvokeInterceptors();
    }

    protected void initInvokeInterceptors() {
        this.invokeInterceptors = null;

        Map<String, InvokeInterceptor> matchingBeans =
                BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, InvokeInterceptor.class, true, false);
        if (!matchingBeans.isEmpty()) {
            this.invokeInterceptors = new ArrayList<InvokeInterceptor>(matchingBeans.values());
            AnnotationAwareOrderComparator.sort(this.invokeInterceptors);
        }
    }
}
