package com.yaojiafeng.exportgateway.web.model;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.Renderable;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.Writer;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/12 下午2:20 $
 */
public class ViewModuleTool implements Renderable {

    private ApplicationContext applicationContext;

    private ViewModuleParameter viewModuleParameter;

    public ViewModuleTool module(String moduleName) {
        ViewModuleParameter viewModuleParameter = getViewModuleParameter();
        viewModuleParameter.setModuleName(moduleName);
        return this;
    }

    public ViewModuleTool view(String viewName) {
        ViewModuleParameter viewModuleParameter = getViewModuleParameter();
        viewModuleParameter.setViewName(viewName);
        return this;
    }

    public ViewModuleTool parameter(String name, Object value) {
        ViewModuleParameter viewModuleParameter = getViewModuleParameter();
        viewModuleParameter.put(name, value);
        return this;
    }


    public ViewModuleTool(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer) throws IOException, MethodInvocationException, ParseErrorException, ResourceNotFoundException {
        ViewModuleParameter viewModuleParameter = getViewModuleParameter();

        ViewModule viewModule = applicationContext.getBean(viewModuleParameter.getModuleName(), ViewModule.class);
        viewModule.render(context, writer, viewModuleParameter);

        //重置属性
        setViewModuleParameter(null);
        return true;
    }


    public ViewModuleParameter getViewModuleParameter() {
        if (viewModuleParameter == null) {
            viewModuleParameter = new ViewModuleParameter();
        }
        return viewModuleParameter;
    }

    public void setViewModuleParameter(ViewModuleParameter viewModuleParameter) {
        this.viewModuleParameter = viewModuleParameter;
    }
}
