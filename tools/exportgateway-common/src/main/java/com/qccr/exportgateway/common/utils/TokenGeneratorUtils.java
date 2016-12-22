/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.common.utils;

import com.qccr.exportgateway.common.Constants;

import java.util.Date;
import java.util.Random;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/8/5 上午10:55 $
 */
public class TokenGeneratorUtils {

    public static void main(String[] args) {
        System.out.println(token());
        System.out.println(token());
    }

    public static String token() {
        String now = DateUtils.format(new Date(), Constants.YYYYMMDDHHMMSS_SIMPLE);
        Random random = new Random();
        return String.format("%s%06d", now, random.nextInt(1000000));
    }

}
