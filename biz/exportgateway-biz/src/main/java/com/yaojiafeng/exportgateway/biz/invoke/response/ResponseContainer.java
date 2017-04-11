package com.yaojiafeng.exportgateway.biz.invoke.response;

/**
 * 返回容器
 *
 * 目前定义返回值都为字符串
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午7:02 $
 */
public class ResponseContainer {
    private String body;

    private StateCode stateCode;

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
}
