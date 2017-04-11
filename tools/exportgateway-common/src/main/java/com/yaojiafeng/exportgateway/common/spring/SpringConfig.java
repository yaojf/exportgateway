package com.yaojiafeng.exportgateway.common.spring;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 2017/1/9 下午4:59 $
 */
public class SpringConfig {
    private final Map<String, String> props = Maps.newHashMap();
    private static SpringConfig config = null;

    private SpringConfig() {
    }

    private static SpringConfig get() {
        if (config == null) {
            synchronized (SpringConfig.class) {
                if (config == null) {
                    config = new SpringConfig();
                }
            }
        }

        return config;
    }

    public static void set(Map<String, String> map) {
        get().props.putAll(map);
    }

    public static String get(String key, String defaultStr) {
        return StringUtils.defaultIfBlank(get().props.get(key), defaultStr);
    }

    public static String get(String key) {
        return get().props.get(key);
    }
}
