package com.yaojiafeng.exportgateway.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/7 下午6:47 $
 */
public class UrlActiveInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(UrlActiveInterceptor.class);

    private static final String APP = "/app/";
    private static final String METHOD = "/method/";
    private static final String EXTERNAL_SYSTEM = "/external-system/";
    private static final String APP_METHOD = "/app-method/";

    private static final String CATEGORY = "category";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        if (path.startsWith(APP)) {
            request.setAttribute(CATEGORY, APP);
        } else if (path.startsWith(METHOD)) {
            request.setAttribute(CATEGORY, METHOD);
        } else if (path.startsWith(EXTERNAL_SYSTEM)) {
            request.setAttribute(CATEGORY, EXTERNAL_SYSTEM);
        } else if (path.startsWith(APP_METHOD)) {
            request.setAttribute(CATEGORY, APP_METHOD);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
