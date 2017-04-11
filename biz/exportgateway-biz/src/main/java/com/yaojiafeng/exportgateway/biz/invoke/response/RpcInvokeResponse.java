package com.yaojiafeng.exportgateway.biz.invoke.response;

import com.yaojiafeng.exportgateway.facade.ogw.protocol.EGProtocol;

/**
 * rpc调用的返回值
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午4:06 $
 */
public class RpcInvokeResponse {

    private StateCode stateCode;

    private String body;

    private EGProtocol egProtocol;

    public StateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public EGProtocol getEgProtocol() {
        return egProtocol;
    }

    public void setEgProtocol(EGProtocol egProtocol) {
        this.egProtocol = egProtocol;
    }
}
