/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.yaojiafeng.exportgateway.web.task;

import com.yaojiafeng.exportgateway.biz.invoke.check.InvokeChecker;
import com.yaojiafeng.exportgateway.common.utils.LogUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 因为是本地缓存
 * <p>
 * 所以不做成分布式任务,每天2点刷新
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/31 上午10:29 $
 */
@Component
public class MethodConfigFlushTask {
    private static final Logger logger = LoggerFactory.getLogger(MethodConfigFlushTask.class);

    @Autowired
    private List<InvokeChecker> invokeCheckerList;

    @Scheduled(cron = "0 0 2 * * ?")
    public void flush() {
        LogUtils.info(logger, "开始刷新方法配置本地缓存");
        long start = System.currentTimeMillis();

        if (CollectionUtils.isNotEmpty(invokeCheckerList)) {
            for (InvokeChecker invokeChecker : invokeCheckerList) {
                invokeChecker.init();
            }
        }

        LogUtils.info(logger, "结束刷新方法配置本地缓存,耗时:{}ms", System.currentTimeMillis() - start);
    }



}
