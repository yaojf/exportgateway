/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.web.interceptor;

import com.qccr.exportgateway.biz.common.session.UserSession;
import com.qccr.exportgateway.common.Constants;
import com.qccr.exportgateway.common.utils.DateUtils;
import com.qccr.exportgateway.common.utils.IPUtils;
import com.qccr.exportgateway.common.utils.LogUtils;
import com.qccr.exportgateway.web.annotation.Login;
import com.qccr.exportgateway.web.controller.RootController;
import com.qccr.exportgateway.web.cookie.CookieHelper;
import com.qccr.exportgateway.web.session.UserSessionContainer;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/8/5 上午11:26 $
 */
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    private CookieHelper cookieHelper;

    private UserSessionContainer userSessionContainer;

    public void setCookieHelper(CookieHelper cookieHelper) {
        this.cookieHelper = cookieHelper;
    }

    public void setUserSessionContainer(UserSessionContainer userSessionContainer) {
        this.userSessionContainer = userSessionContainer;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler != null && handler instanceof HandlerMethod) {
            Cookie cookie = cookieHelper.getCookie(request);
            String sessionId = "";
            if (cookie == null) {
                sessionId = UUID.randomUUID().toString();
                cookieHelper.addCookie(response, sessionId);
            }
            request.setAttribute(RootController.SESSION_ID, sessionId);

            final HandlerMethod hm = (HandlerMethod) handler;

            Login login = getAnnotation(hm.getMethod(), Login.class);

            if (login != null && login.login()) {//登录校验
                UserSession userSession = userSessionContainer.findUserBySessionId(sessionId);
                if (userSession == null) {
                    response.sendRedirect("/user/login?service="
                            + URLEncoder.encode(getFullUrl(request), Constants.ENCODING));
                    return false;
                }
                request.setAttribute(RootController.THIS_USER, userSession);
            }

            logRequest(request);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String sessionId = (String) request.getAttribute(RootController.SESSION_ID);
        if (sessionId != null) {//刷新缓存
            UserSession userSession = (UserSession) request.getAttribute(RootController.THIS_USER);
            if (userSession != null) {
                userSessionContainer.setUserBySessionId(sessionId, userSession);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private <T extends Annotation> T getAnnotation(Method method, Class<T> clazz) {
        T t = method.getAnnotation(clazz);
        if (t == null) {
            t = method.getDeclaringClass().getAnnotation(clazz);
        }
        return t;
    }

    private String getFullUrl(HttpServletRequest request) {
        return request.getServletPath() + "?" + getReqInfo(request);
    }

    private String getReqInfo(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        String param = "";
        if (req.getMethod().equals("GET")) {
            param = req.getQueryString() != null ? req.getQueryString() : "";
        } else if (req.getMethod().equals("POST")) {
            Map<String, String[]> reqParameterMap = req.getParameterMap();
            for (Map.Entry<String, String[]> entry : reqParameterMap.entrySet()) {
                param += entry.getKey() + "=" + (ArrayUtils.isNotEmpty(entry.getValue()) ? entry.getValue()[0] : "") + "&";
            }
            if (!"".equals(param)) {
                param = param.substring(0, param.length() - 1);
            }
        }
        sb.append(param);

        return sb.toString();
    }

    private void logRequest(HttpServletRequest request) {
        String ip = IPUtils.getRemoteHost(request);
        String uri = request.getRequestURI();
        String reqInfo = getReqInfo(request);
        LogUtils.info(logger, "IP为[{}]访问了系统,TIME为[{}],URI为[{}],请求参数为[{}]", ip, DateUtils.format(new Date()), uri, reqInfo);
    }

}
