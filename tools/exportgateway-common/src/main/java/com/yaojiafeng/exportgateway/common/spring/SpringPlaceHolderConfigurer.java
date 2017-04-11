package com.yaojiafeng.exportgateway.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Properties;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 2017/1/9 下午5:01 $
 */
public class SpringPlaceHolderConfigurer extends PropertyPlaceholderConfigurer {

    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        HashMap map = new HashMap(props);
        SpringConfig.set(map);
        super.processProperties(beanFactoryToProcess, props);
    }
}
