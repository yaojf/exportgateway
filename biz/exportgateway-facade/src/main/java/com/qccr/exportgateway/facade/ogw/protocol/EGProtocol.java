/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.facade.ogw.protocol;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/6 下午3:15 $
 */

public class EGProtocol implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String SUCCESS = "EG1000";

    public static final String FAILURE = "EG9999";

    /**
     * 应用id
     */
    private String appID;
    /**
     * 应用分配的token
     */
    private String token;
    /**
     * 调用id,用于查询日志
     */
    private String invokeID;
    /**
     * 外部系统名称
     */
    private String externalSystemName;
    /**
     * 调用的方法
     */
    private String method;
    /**
     * 请求编码
     */
    private String charset = "UTF-8";
    /**
     * 发送请求的时间 yyyy-MM-dd HH:mm:ss
     */
    private String requestTimestamp;
    /**
     * 请求响应的时间 yyyy-MM-dd HH:mm:ss
     */
    private String responseTimestamp;
    /**
     * 接口版本目前写死1.0
     */
    private String ver = "1.0";
    /**
     * 请求参数或者返回参数
     * 请求是个json字符串
     * <p>
     * 返回就是返回的结果
     * <p>
     */
    private String body;
    /**
     * 返回码
     * <p>
     * EG9999
     * <p>
     * EG1000
     */
    private String code;
    /**
     * 返回信息
     * <p>
     * EG9999 : 调用失败
     * <p>
     * EG1000 : 调用成功
     */
    private String msg;
    /**
     * 具体返回码
     * <p>
     * 10000:成功
     * <p>
     * 90001:不存在的appId
     * <p>
     * 90002:Token不匹配
     * <p>
     * 90003:当前appId不可用
     * <p>
     * 90004:不存在的外部系统
     * <p>
     * 90005:当前外部系统不可用
     * <p>
     * 90006:不存在的外部服务
     * <p>
     * 90007:当前外部服务不可用
     * <p>
     * 90008:无此接口调用权限
     * <p>
     * 90009:验证回签失败
     * <p>
     * 90010:系统异常
     */
    private String subCode;
    /**
     * 具体错误信息,详见subCode说明
     */
    private String subMsg;

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInvokeID() {
        return invokeID;
    }

    public void setInvokeID(String invokeID) {
        this.invokeID = invokeID;
    }

    public String getExternalSystemName() {
        return externalSystemName;
    }

    public void setExternalSystemName(String externalSystemName) {
        this.externalSystemName = externalSystemName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(String requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public String getResponseTimestamp() {
        return responseTimestamp;
    }

    public void setResponseTimestamp(String responseTimestamp) {
        this.responseTimestamp = responseTimestamp;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public boolean isSuccess() {
        return SUCCESS.equals(this.code);
    }

    @Override
    public String toString() {
        return "EGProtocol{" +
                "appID='" + appID + '\'' +
                ", token='" + token + '\'' +
                ", invokeID='" + invokeID + '\'' +
                ", externalSystemName='" + externalSystemName + '\'' +
                ", method='" + method + '\'' +
                ", charset='" + charset + '\'' +
                ", requestTimestamp='" + requestTimestamp + '\'' +
                ", responseTimestamp='" + responseTimestamp + '\'' +
                ", ver='" + ver + '\'' +
                ", body='" + body + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                '}';
    }
}
