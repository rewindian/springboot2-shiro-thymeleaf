package com.sst.core.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * redis操作类
 */
@Component
public class RedisOperator {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 带过期时间存数据 单位默认秒
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public void setDataWithExpire(Serializable key, Serializable value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    public void setData(Serializable key, Serializable value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取过期时间 单位默认秒
     *
     * @param key
     * @return
     */
    public long getExpire(Serializable key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public Object getData(Serializable key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteData(Serializable key) {
        redisTemplate.delete(key);
    }

    public boolean hasKey(Serializable key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param seconds
     */
    public void setExpire(Serializable key, long seconds) {
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 向List推数据
     *
     * @param key
     * @param value
     */
    public void lpush(Serializable key, Serializable value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 从List阻塞取数据
     *
     * @param key
     * @return
     */
    public Object brpop(Serializable key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    public Object brpop(Serializable key, long seconds) {
        return redisTemplate.opsForList().rightPop(key, seconds, TimeUnit.SECONDS);
    }
}

