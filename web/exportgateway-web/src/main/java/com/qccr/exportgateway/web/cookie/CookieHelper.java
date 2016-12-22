/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.web.cookie;

import com.qccr.exportgateway.common.utils.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie帮助类
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/8/5 下午12:00 $
 */
public class CookieHelper {
    private String domain;

    private String path;

    private String name;

    private boolean httpOnly;

    private Integer maxAge;

    public CookieHelper(Integer maxAge, String domain, String path, String name, boolean httpOnly) {
        this.maxAge = maxAge;
        this.domain = domain;
        this.path = path;
        this.name = name;
        this.httpOnly = httpOnly;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHttpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Cookie getCookie(HttpServletRequest request) {
        Cookie cookie = CookieUtils.getCookie(request, name);
        return cookie;
    }

    public void addCookie(HttpServletResponse response,
                          String value) {
        CookieUtils.setCookie(response, name, value, maxAge, domain, path, httpOnly);
    }

}
