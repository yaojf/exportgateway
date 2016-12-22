/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.web.model.module;

import com.qccr.exportgateway.biz.service.AppService;
import com.qccr.exportgateway.dal.entity.App;
import com.qccr.exportgateway.web.model.AbstractViewModule;
import com.qccr.exportgateway.web.model.ViewModuleParameter;
import org.apache.velocity.context.InternalContextAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/8/2 上午11:35 $
 */
@Component("appModule")
public class AppModule extends AbstractViewModule {

    @Autowired
    private AppService appService;

    @Override
    protected void addModule(InternalContextAdapter context, ViewModuleParameter viewModuleParameter) {
        Integer appId = (Integer) viewModuleParameter.get("appId");
        String key = (String) viewModuleParameter.get("key");

        App app = appService.findById(appId);

        context.put("app", app);
        context.put("key", key);
    }
}
