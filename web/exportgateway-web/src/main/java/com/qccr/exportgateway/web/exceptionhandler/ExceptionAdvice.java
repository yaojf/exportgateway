/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.web.exceptionhandler;

import com.qccr.exportgateway.common.utils.LogUtils;
import com.qccr.exportgateway.web.response.ResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/27 上午10:59 $
 */
@ControllerAdvice
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        LogUtils.error(logger, getExceptionInfo(ex), ex);
        String detail = null;
        PrintWriter printWriter = null;
        try {
            StringWriter stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);
            ex.printStackTrace(printWriter);
            detail = stringWriter.toString();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }

        model.addAttribute("detail", detail);
        model.addAttribute("errorMsg", ex.getMessage());
        return "error";
    }


    private String getExceptionInfo(Exception ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("异常类型[" + ClassUtils.getShortName(ex.getClass())).append("],异常原因[").append(ex.getMessage()).append("]");
        return sb.toString();
    }


}
