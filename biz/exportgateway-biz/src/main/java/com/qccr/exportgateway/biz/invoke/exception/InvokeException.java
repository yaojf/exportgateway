/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.invoke.exception;

import com.qccr.exportgateway.biz.invoke.response.StateCode;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 下午4:50 $
 */
public class InvokeException extends RuntimeException {

    private StateCode stateCode;

    public InvokeException(StateCode stateCode) {
        super(stateCode.getSubMsg());
        this.stateCode = stateCode;
    }

    public InvokeException(String message, StateCode stateCode) {
        super(message);
        this.stateCode = stateCode;
    }

    public InvokeException(String message, Throwable cause, StateCode stateCode) {
        super(message, cause);
        this.stateCode = stateCode;
    }

    public StateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }
}
