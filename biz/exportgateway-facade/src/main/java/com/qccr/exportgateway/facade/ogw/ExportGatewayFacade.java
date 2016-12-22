/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.facade.ogw;

import com.qccr.exportgateway.facade.ogw.protocol.EGProtocol;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/6 下午12:01 $
 */
public interface ExportGatewayFacade {
    /**
     * 调用出口网关
     * @param egProtocol 协议规则
     * @return
     */
    EGProtocol invoke(EGProtocol egProtocol);
}
