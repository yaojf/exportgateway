package com.yaojiafeng.exportgateway.web.model.module;

import com.yaojiafeng.exportgateway.common.Page;
import com.yaojiafeng.exportgateway.web.model.AbstractViewModule;
import com.yaojiafeng.exportgateway.web.model.ViewModuleParameter;
import org.apache.velocity.context.InternalContextAdapter;
import org.springframework.stereotype.Component;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/21 下午5:47 $
 */
@Component("pageModule")
public class PageModule extends AbstractViewModule {
    @Override
    protected void addModule(InternalContextAdapter context, ViewModuleParameter viewModuleParameter) {
        Page page = (Page) viewModuleParameter.get("page");
        String url = (String) viewModuleParameter.get("url");
        context.put("page", page);
        context.put("url", url);
    }
}
