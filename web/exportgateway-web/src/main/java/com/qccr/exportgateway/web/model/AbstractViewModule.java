/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.exportgateway.web.model;

import com.qccr.exportgateway.common.Constants;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.InternalContextAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import java.io.Writer;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/12 下午3:06 $
 */
public abstract class AbstractViewModule implements ViewModule {

    @Autowired
    private VelocityConfigurer velocityConfigurer;

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, ViewModuleParameter viewModuleParameter) {
        addModule(context, viewModuleParameter);

        String viewName = viewModuleParameter.getViewName();

        VelocityEngine velocityEngine = velocityConfigurer.getVelocityEngine();

        return velocityEngine.mergeTemplate(viewName, Constants.ENCODING, context, writer);
    }

    protected abstract void addModule(InternalContextAdapter context,
                                      ViewModuleParameter viewModuleParameter);


}
