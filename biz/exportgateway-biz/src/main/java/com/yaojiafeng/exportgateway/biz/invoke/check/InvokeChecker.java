package com.yaojiafeng.exportgateway.biz.invoke.check;

import com.yaojiafeng.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.yaojiafeng.exportgateway.biz.invoke.response.RpcInvokeResponse;
import org.springframework.beans.factory.InitializingBean;

/**
 * 暂定所有实现类下面的缓存走本地缓存
 *
 * 校验所有配置信息正确性
 *
 * app配置,method配置,externalSystem配置,appMethod配置
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/28 下午7:27 $
 */
public interface InvokeChecker extends InitializingBean {

    void doCheck(RpcInvokeRequest request, RpcInvokeResponse response);

    void init();
}
