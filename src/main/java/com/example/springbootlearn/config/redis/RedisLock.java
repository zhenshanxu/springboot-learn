package com.example.springbootlearn.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

/**
 * @Project springboot-learn
 * @Description redis 分布式锁
 * @Author xuzhenshan
 * @Date 2023/12/27 16:21:10
 * @Version 1.0
 */

@Slf4j
@Component
public class RedisLock {

    private final StringRedisTemplate redisTemplate;

    public RedisLock(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * redis 加锁
     *
     * @param key   锁-名称
     * @param value 当前时间+ 超时时间
     * @return true / false 锁添加状态
     */
    public boolean lock(String key, String value) {
        if (Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, value))) {
            log.info("[Redis 分布式锁：{}]-直接加锁成功!", key);
            return Boolean.TRUE;
        }
        String currentValue = redisTemplate.opsForValue().get(key);
        //如果锁过期
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获取上有个锁的时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                log.info("[Redis 分布式锁：{}]-续期加锁成功!", key);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 解锁
     *
     * @param key 锁-名称
     * @param value 当前时间+ 超时时间
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
                log.info("[Redis 分布式锁：{}]-解锁成功", key);
            }
        } catch (Exception e) {
            log.error("[Redis 分布式锁：{}]-解锁异常,{}",key, e.getMessage());
        }
    }
}
