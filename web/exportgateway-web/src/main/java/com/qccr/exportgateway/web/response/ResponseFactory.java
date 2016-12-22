/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.web.response;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 上午11:04 $
 */
public class ResponseFactory {


    public static ResponseBean newInstance(int code, String msg) {
        return new ResponseBean(code, msg);
    }

}
