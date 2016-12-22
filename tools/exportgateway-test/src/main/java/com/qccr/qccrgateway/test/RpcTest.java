package com.qccr.qccrgateway.test; /**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */

import com.alibaba.fastjson.JSONObject;
import com.qccr.exportgateway.facade.ogw.ExportGatewayFacade;
import com.qccr.exportgateway.facade.ogw.protocol.EGProtocol;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/12 上午10:41 $
 */
public class RpcTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext-rpc-consumer.xml");
        ExportGatewayFacade qCCRGatewayFacade = classPathXmlApplicationContext.getBean("exportGatewayFacade", ExportGatewayFacade.class);

        EGProtocol egProtocol = new EGProtocol();
        egProtocol.setAppID("20160819113025272298");
        egProtocol.setExternalSystemName("baidu_map");
        egProtocol.setMethod("com.baidu.map.api.geocoder.v2");
        egProtocol.setToken("20160819113026382185");

        JSONObject json=new JSONObject();
        json.put("location","38.76623,116.43213");
        json.put("output","json");
        json.put("ak","vAMLRotvbDyXbUVzkdPjjzMapgAeVzYp");
        egProtocol.setBody(json.toJSONString());

        EGProtocol invoke = qCCRGatewayFacade.invoke(egProtocol);

        System.out.println(invoke.getBody());

        System.exit(0);
    }
}
