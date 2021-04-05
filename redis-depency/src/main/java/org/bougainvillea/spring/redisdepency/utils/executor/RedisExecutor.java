package org.bougainvillea.spring.redisdepency.utils.executor;

import lombok.Getter;
import lombok.Setter;
import org.bougainvillea.spring.redisdepency.utils.RedisOperateData;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

/**
 * redis 抽象执行器
 * @author caddy
 */
@Getter
@Setter
public abstract class RedisExecutor {
    protected RedisTemplate<String, Object> redisTemplate;
    protected RedisExecutor nextExecutor;

    protected RedisExecutor(RedisTemplate<String, Object> redisTemplate) {
        Assert.notNull(redisTemplate,"redisTemplate must be not null");
        this.redisTemplate = redisTemplate;
    }

    public abstract boolean writeRequest(RedisOperateData data);
    public abstract RedisOperateData readRequest(RedisOperateData data);

}


