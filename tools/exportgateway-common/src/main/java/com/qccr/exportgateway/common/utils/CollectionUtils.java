/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.common.utils;

import java.util.Collection;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/7 下午4:46 $
 */
public class CollectionUtils {

    public static boolean isNotEmpty(Collection coll) {
        return !CollectionUtils.isEmpty(coll);
    }

    public static boolean isEmpty(Collection coll) {
        return (coll == null || coll.isEmpty());
    }
}
