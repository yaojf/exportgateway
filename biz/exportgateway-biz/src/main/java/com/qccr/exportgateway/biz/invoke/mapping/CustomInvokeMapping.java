/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.invoke.mapping;

import com.qccr.exportgateway.biz.invoke.annotation.AnnotaionBeanFinder;
import com.qccr.exportgateway.biz.invoke.request.InvokeExecutionChain;
import com.qccr.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.qccr.exportgateway.biz.invoke.response.RpcInvokeResponse;
import com.qccr.exportgateway.facade.ogw.protocol.EGProtocol;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午3:42 $
 */
@Order(2)
@Component
public class CustomInvokeMapping extends AbstractInvokeMapping {

    private final Map<MethodInfo, InvokeMethod> mappingLookup = new LinkedHashMap<MethodInfo, InvokeMethod>();

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        initInvokeMethods();
    }

    protected void initInvokeMethods() {
        //获取InvokeController标注的处理器类
        List<String> beanNames = AnnotaionBeanFinder.findAnnotatedBeans(getApplicationContext());
        if (CollectionUtils.isNotEmpty(beanNames)) {
            for (String beanName : beanNames) {
                Class<?> handlerType = getApplicationContext().getType(beanName);
                //获取真正的类型
                final Class<?> userType = ClassUtils.getUserClass(handlerType);
                //删选出methd方法
                Map<Method, MethodInfo> methods = MethodIntrospector.selectMethods(userType,
                        new MethodIntrospector.MetadataLookup<MethodInfo>() {
                            @Override
                            public MethodInfo inspect(Method method) {
                                return getMappingForMethod(method, userType);
                            }
                        });

                for (Map.Entry<Method, MethodInfo> entry : methods.entrySet()) {
                    registerInvokeMethod(beanName, entry.getKey(), entry.getValue());
                }

            }
        }
    }

    protected MethodInfo getMappingForMethod(Method method, Class<?> clazz) {
        MethodInfo info = createMethodInfo(method);
        if (info != null) {
            MethodInfo typeInfo = createMethodInfo(clazz);
            if (typeInfo != null) {
                info = typeInfo.combine(info);
            }
        }
        return info;
    }

    private MethodInfo createMethodInfo(AnnotatedElement element) {
        com.qccr.exportgateway.biz.invoke.annotation.Method method = AnnotationUtils.findAnnotation(element, com.qccr.exportgateway.biz.invoke.annotation.Method.class);
        if (method != null) {
            return MethodInfo.builder().name(method.value()).systemName(method.systemName()).build();
        }
        return null;
    }

    protected void registerInvokeMethod(String beanName, Method method, MethodInfo methodInfo) {
        InvokeMethod invokeMethod = createInvokeMethod(beanName, method);
        checkMethodMapping(invokeMethod, methodInfo);
        this.mappingLookup.put(methodInfo, invokeMethod);
    }

    /**
     * 校验methodinfo信息是否全和重复校验
     *
     * @param invokeMethod
     * @param methodInfo
     */
    protected void checkMethodMapping(InvokeMethod invokeMethod, MethodInfo methodInfo) {
        if (methodInfo.isAnyBlank()) {
            throw new BeanCreationException("方法注解配置不正确,class=[" + invokeMethod.getBeanType() + "],method=[" + invokeMethod.getMethod().getName() + "]");
        }
        if (this.mappingLookup.get(methodInfo) != null) {
            throw new BeanCreationException("方法信息配置重复,class=[" + invokeMethod.getBeanType() + "],method=[" + invokeMethod.getMethod().getName() + "]");
        }

        //目前方法入参和出参固定,这里再加个校验
        Method method = invokeMethod.getMethod();
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes == null || parameterTypes.length != 2 || parameterTypes[0] != RpcInvokeRequest.class || parameterTypes[1] != RpcInvokeResponse.class) {
            throw new BeanCreationException("方法入参不正确,class=[" + invokeMethod.getBeanType() + "],method=[" + invokeMethod.getMethod().getName() + "]");
        }

        Class<?> returnType = method.getReturnType();
        if (returnType != String.class) {
            throw new BeanCreationException("方法返回值不正确,class=[" + invokeMethod.getBeanType() + "],method=[" + invokeMethod.getMethod().getName() + "]");
        }
    }

    protected InvokeMethod createInvokeMethod(String beanName, Method method) {
        InvokeMethod invokeMethod = new InvokeMethod(beanName, method, getApplicationContext());
        return invokeMethod;
    }


    @Override
    public InvokeExecutionChain getHandler(RpcInvokeRequest request) throws Exception {
        com.qccr.exportgateway.dal.entity.Method method = request.getMethod();
        EGProtocol egProtocol = request.getEgProtocol();
        if (method.getInvokeType().equals(INVOKE_TYPE_CUSTOM)) {
            MethodInfo methodInfo = new MethodInfo(egProtocol.getMethod(), egProtocol.getExternalSystemName());
            InvokeMethod invokeMethod = this.mappingLookup.get(methodInfo);
            if (invokeMethod != null) {
                InvokeExecutionChain invokeExecutionChain = new InvokeExecutionChain(invokeMethod, getInvokeInterceptors());
                return invokeExecutionChain;
            }
        }

        return null;
    }
}
