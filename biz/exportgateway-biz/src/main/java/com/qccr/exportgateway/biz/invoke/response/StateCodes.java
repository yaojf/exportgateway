/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.invoke.response;

/**
 * 状态码常量类
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午4:52 $
 */
public class StateCodes {

    public static final String FAILURE_CODE = "EG9999";
    public static final String FAILURE_MSG = "调用失败";
    public static final String SUCCESS_CODE = "EG1000";
    public static final String SUCCESS_MSG = "调用成功";

    public static final StateCode SUCCESS = new StateCode(SUCCESS_CODE, SUCCESS_MSG, "10000", "成功");
    public static final StateCode DOES_NOT_EXIST_APPID = new StateCode(FAILURE_CODE, FAILURE_MSG, "90001", "不存在的appId");
    public static final StateCode TOKEN_DOES_NOT_MATCH = new StateCode(FAILURE_CODE, FAILURE_MSG, "90002", "Token不匹配");
    public static final StateCode THE_CURRENT_APPID_IS_NOT_AVAILABLE = new StateCode(FAILURE_CODE, FAILURE_MSG, "90003", "当前appId不可用");
    public static final StateCode THE_EXTERNAL_SYSTEM_DOES_NOT_EXIST = new StateCode(FAILURE_CODE, FAILURE_MSG, "90004", "不存在的外部系统");
    public static final StateCode THE_CURRENT_EXTERNAL_SYSTEM_IS_NOT_AVAILABLE = new StateCode(FAILURE_CODE, FAILURE_MSG, "90005", "当前外部系统不可用");
    public static final StateCode NON_EXISTING_EXTERNAL_SERVICES = new StateCode(FAILURE_CODE, FAILURE_MSG, "90006", "不存在的外部服务");
    public static final StateCode THE_CURRENT_EXTERNAL_SERVICE_IS_NOT_AVAILABLE = new StateCode(FAILURE_CODE, FAILURE_MSG, "90007", "当前外部服务不可用");
    public static final StateCode NO_RIGHT_TO_CALL_THIS_INTERFACE = new StateCode(FAILURE_CODE, FAILURE_MSG, "90008", "无此接口调用权限");
    public static final StateCode VERIFY_SIGNATURE_FAILED= new StateCode(FAILURE_CODE, FAILURE_MSG, "90009", "验证回签失败");
    public static final StateCode ABNORMAL_SYSTEM = new StateCode(FAILURE_CODE, FAILURE_MSG, "90010", "系统异常");

}
