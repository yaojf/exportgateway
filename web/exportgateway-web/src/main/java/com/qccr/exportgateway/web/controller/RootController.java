/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.web.controller;

import com.qccr.exportgateway.biz.common.session.UserSession;
import com.qccr.exportgateway.common.Constants;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/8/5 下午3:27 $
 */
public class RootController {

    public static final String THIS_USER = "thisUser";

    public static final String SESSION_ID = "sessionId";

    public static final String HOME = "/";


    public UserSession getUserSession(HttpServletRequest request) {
        if (request != null) {
            UserSession userSession = (UserSession) request.getAttribute(THIS_USER);
            if (userSession != null) {
                return userSession;
            }
        }
        return null;
    }


    public String getUserSessionId(HttpServletRequest request) {
        return (String) request.getAttribute(SESSION_ID);
    }

    public String redirect(String url, String service) {
        try {
            if (StringUtils.isNotEmpty(service)) {
                return "redirect:" + URLDecoder.decode(service, Constants.ENCODING);
            } else {
                return "redirect:" + url;
            }
        } catch (Exception ex) {
            return "redirect:" + url;
        }
    }


}
