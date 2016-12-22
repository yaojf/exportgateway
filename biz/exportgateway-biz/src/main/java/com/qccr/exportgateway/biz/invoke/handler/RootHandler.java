/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.invoke.handler;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/8/19 下午1:13 $
 */
public class RootHandler {


    protected String getRequestParam(JSONObject json) {
        StringBuilder sb = new StringBuilder();
        for (String key : json.keySet()) {
            if (sb.length() != 0) {
                sb.append("&");
            }
            sb.append(key).append("=").append(json.get(key));
        }
        return sb.toString();
    }


}
