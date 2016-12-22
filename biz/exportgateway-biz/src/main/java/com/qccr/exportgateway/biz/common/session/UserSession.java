/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.biz.common.session;

import java.io.Serializable;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/8/5 下午3:00 $
 */
public class UserSession implements Serializable{


    /**
     * 用户ID
     */

    private Integer userId = 0;

    /**
     * 用户名
     */
    private String username = "admin";


    public UserSession() {
    }

    public UserSession(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
