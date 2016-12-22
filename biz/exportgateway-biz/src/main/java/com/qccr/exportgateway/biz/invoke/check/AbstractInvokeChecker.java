/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.invoke.check;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/29 下午5:18 $
 */
public abstract class AbstractInvokeChecker implements InvokeChecker {

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
