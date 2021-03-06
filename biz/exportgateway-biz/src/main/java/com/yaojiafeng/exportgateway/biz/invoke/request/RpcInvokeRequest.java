package com.yaojiafeng.exportgateway.biz.invoke.request;

import com.yaojiafeng.exportgateway.dal.entity.App;
import com.yaojiafeng.exportgateway.dal.entity.AppMethod;
import com.yaojiafeng.exportgateway.dal.entity.ExternalSystem;
import com.yaojiafeng.exportgateway.dal.entity.Method;
import com.yaojiafeng.exportgateway.facade.ogw.protocol.EGProtocol;

/**
 * rpc调用请求类
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午4:05 $
 */
public class RpcInvokeRequest {

    private EGProtocol egProtocol;

    private Method method;

    private App app;

    private AppMethod appMethod;

    private ExternalSystem externalSystem;


    public EGProtocol getEgProtocol() {
        return egProtocol;
    }

    public void setEgProtocol(EGProtocol egProtocol) {
        this.egProtocol = egProtocol;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public AppMethod getAppMethod() {
        return appMethod;
    }

    public void setAppMethod(AppMethod appMethod) {
        this.appMethod = appMethod;
    }

    public ExternalSystem getExternalSystem() {
        return externalSystem;
    }

    public void setExternalSystem(ExternalSystem externalSystem) {
        this.externalSystem = externalSystem;
    }
}
