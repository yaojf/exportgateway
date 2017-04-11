/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.yaojiafeng.exportgateway.biz.invoke.handler.custom;

import com.alibaba.fastjson.JSONObject;
import com.yaojiafeng.exportgateway.biz.invoke.annotation.InvokeController;
import com.yaojiafeng.exportgateway.biz.invoke.annotation.Method;
import com.yaojiafeng.exportgateway.biz.invoke.handler.RootHandler;
import com.yaojiafeng.exportgateway.biz.invoke.request.RpcInvokeRequest;
import com.yaojiafeng.exportgateway.biz.invoke.response.RpcInvokeResponse;
import com.yaojiafeng.exportgateway.common.utils.HttpUtils;
import com.yaojiafeng.exportgateway.facade.ogw.protocol.EGProtocol;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/28 上午11:37 $
 */
@InvokeController
@Method(systemName = "baidu_map")
public class BaiduMapHandler extends RootHandler{


    @Method("com.baidu.map.api.geocoder.v2")
    public String getLocation(RpcInvokeRequest request, RpcInvokeResponse response) {
        com.qccr.exportgateway.dal.entity.Method method = request.getMethod();
        //获取url
        String url = method.getUrl();

        //获取请求参数
        EGProtocol egProtocol = request.getEgProtocol();
        String body = egProtocol.getBody();

        JSONObject json = JSONObject.parseObject(body);

        //开始请求
        String result = HttpUtils.post(url, getRequestParam(json));

        return result;
    }


}
