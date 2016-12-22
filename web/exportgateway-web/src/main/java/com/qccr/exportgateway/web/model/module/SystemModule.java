/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.web.model.module;

import com.qccr.exportgateway.biz.service.ExternalSystemService;
import com.qccr.exportgateway.dal.entity.ExternalSystem;
import com.qccr.exportgateway.web.model.AbstractViewModule;
import com.qccr.exportgateway.web.model.ViewModuleParameter;
import org.apache.velocity.context.InternalContextAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/8/1 下午7:30 $
 */
@Component("systemModule")
public class SystemModule extends AbstractViewModule {

    @Autowired
    private ExternalSystemService externalSystemService;

    @Override
    protected void addModule(InternalContextAdapter context, ViewModuleParameter viewModuleParameter) {
        Integer systemId = (Integer) viewModuleParameter.get("systemId");
        ExternalSystem externalSystem = externalSystemService.findById(systemId);
        context.put("externalSystem", externalSystem);
    }
}
