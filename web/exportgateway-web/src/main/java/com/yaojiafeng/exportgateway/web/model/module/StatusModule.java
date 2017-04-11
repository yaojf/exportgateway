package com.yaojiafeng.exportgateway.web.model.module;

import com.yaojiafeng.exportgateway.web.model.AbstractViewModule;
import com.yaojiafeng.exportgateway.web.model.ViewModuleParameter;
import org.apache.velocity.context.InternalContextAdapter;
import org.springframework.stereotype.Component;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/12 下午3:12 $
 */
@Component("statusModule")
public class StatusModule extends AbstractViewModule {

    @Override
    protected void addModule(InternalContextAdapter context, ViewModuleParameter viewModuleParameter) {
        Byte status = (Byte) viewModuleParameter.get("status");
        context.put("statusName", status == 1 ? "有效" : "失效");
    }
}
