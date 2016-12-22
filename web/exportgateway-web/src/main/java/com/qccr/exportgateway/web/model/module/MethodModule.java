/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.web.model.module;

import com.qccr.exportgateway.biz.service.ExternalSystemService;
import com.qccr.exportgateway.biz.service.MethodService;
import com.qccr.exportgateway.dal.entity.ExternalSystem;
import com.qccr.exportgateway.dal.entity.Method;
import com.qccr.exportgateway.web.model.AbstractViewModule;
import com.qccr.exportgateway.web.model.ViewModuleParameter;
import org.apache.velocity.context.InternalContextAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/8/2 上午11:49 $
 */
@Component("methodModule")
public class MethodModule extends AbstractViewModule {
    @Autowired
    private MethodService methodService;

    @Autowired
    private ExternalSystemService externalSystemService;

    @Override
    protected void addModule(InternalContextAdapter context, ViewModuleParameter viewModuleParameter) {
        Integer methodId = (Integer) viewModuleParameter.get("methodId");
        Method method = methodService.findById(methodId);

        String key = (String) viewModuleParameter.get("key");

        context.put("method", method);
        context.put("key", key);

        if (key.equals("systemName")) {
            ExternalSystem externalSystem = externalSystemService.findById(method.getSystemId());
            context.put("externalSystem", externalSystem);
        }
    }
}
