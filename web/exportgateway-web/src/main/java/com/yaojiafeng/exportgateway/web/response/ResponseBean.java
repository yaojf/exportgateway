package com.yaojiafeng.exportgateway.web.response;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 上午11:05 $
 */
public class ResponseBean {
    private int code;
    private String msg;
    private Object info;

    public ResponseBean(int code, String msg, Object info) {
        this.code = code;
        this.msg = msg;
        this.info = info;
    }

    public ResponseBean(int code, String msg) {
        this(code, msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
}
