package com.yaojiafeng.exportgateway.web.model;

import org.apache.velocity.context.InternalContextAdapter;

import java.io.Writer;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/6/12 下午2:41 $
 */
public interface ViewModule {
    boolean render(InternalContextAdapter context, Writer writer, ViewModuleParameter viewModuleParameter);
}
