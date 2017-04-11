package com.yaojiafeng.exportgateway.biz.invoke.mapping;

import com.yaojiafeng.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.yaojiafeng.exportgateway.biz.invoke.response.ResponseContainer;
import com.yaojiafeng.exportgateway.biz.invoke.response.RpcInvokeResponse;
import com.yaojiafeng.exportgateway.biz.invoke.response.StateCodes;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

/**
 * 反射调用的方法和类的封装
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/29 上午10:23 $
 */
public class InvokeMethod {

    /**
     * 已经解析的bean对象
     */
    private final Object bean;

    private final Method method;

    private final BeanFactory beanFactory;

    private final Class<?> beanType;

    public InvokeMethod(Object bean, Method method, BeanFactory beanFactory) {
        if (bean instanceof String) {
            bean = beanFactory.getBean((String) bean);
        }
        this.bean = bean;
        this.method = method;
        this.beanFactory = beanFactory;
        this.beanType = ClassUtils.getUserClass(this.bean);
    }

    public Object getBean() {
        return bean;
    }

    public Method getMethod() {
        return method;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public Class<?> getBeanType() {
        return beanType;
    }

    public void doInvoke(RpcInvokeRequest request, RpcInvokeResponse response, ResponseContainer rc) throws Exception {
        method.setAccessible(true);
        Object result = method.invoke(bean, request, response);
        String body = (String) result;
        rc.setBody(body);
        rc.setStateCode(StateCodes.SUCCESS);
    }

}
