package com.yaojiafeng.exportgateway.web.model.module;

import com.yaojiafeng.exportgateway.biz.service.ExternalSystemService;
import com.yaojiafeng.exportgateway.dal.entity.ExternalSystem;
import com.yaojiafeng.exportgateway.web.model.AbstractViewModule;
import com.yaojiafeng.exportgateway.web.model.ViewModuleParameter;
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
