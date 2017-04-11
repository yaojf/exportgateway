package com.yaojiafeng.exportgateway.common.utils;

import org.slf4j.Logger;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/7 上午11:44 $
 */
public class LogUtils {
    public static void error(Logger logger, String msg, Throwable throwable) {
        if (logger.isErrorEnabled()) {
            logger.error(msg, throwable);
        }
    }

    public static void info(Logger logger, String msg, Object... param) {
        if (logger.isInfoEnabled()) {
            logger.info(msg, param);
        }
    }


}
