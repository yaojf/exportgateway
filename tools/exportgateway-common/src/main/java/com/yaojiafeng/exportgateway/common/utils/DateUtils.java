package com.yaojiafeng.exportgateway.common.utils;

import com.yaojiafeng.exportgateway.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/7 上午11:35 $
 */
public class DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static String format(Date date) {
        return format(date, Constants.YYYYMMDDHHMMSS);
    }

    public static String format(Date date, String format) {
        if (date == null || format == null) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception ex) {
            LogUtils.error(logger, "格式化日志失败", ex);
            return null;
        }
    }



}
