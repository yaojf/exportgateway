package com.yaojiafeng.exportgateway.common.utils;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * Created by yaojiafeng on 16/2/20.
 */
public class RedisUtils {
    private static ValueOperations<String, Object> valueOperations = null;

    public static ValueOperations<String, Object> getValueOperations() {
        return valueOperations;
    }

    public static void setValueOperations(ValueOperations<String, Object> valueOperations) {
        RedisUtils.valueOperations = valueOperations;
    }

    public static void set(String key, Object value, int expire) {
        valueOperations.set(key, value, expire, TimeUnit.SECONDS);
    }

    public static void set(String key, Object value) {
        valueOperations.set(key, value);
    }

    public static Object get(String key) {
        return valueOperations.get(key);
    }

    public static void delete(String key) {
        valueOperations.getOperations().delete(key);
    }

    private static RedisTemplate<String, Object> redisTemplate = null;

    public static RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public static void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

}
