package org.bougainvillea.spring.redisdepency.utils.executor;

import org.bougainvillea.spring.redisdepency.utils.RedisOperateData;
import org.bougainvillea.spring.redisdepency.utils.RedisOperates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis keys 命令操作
 * @author caddy
 */
@Component
public class RedisKeysExecutor extends RedisExecutor{

    @Autowired
    public RedisKeysExecutor(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public boolean writeRequest(RedisOperateData data) {
        if(RedisOperates.REDIS_KEYS_OPERATE.equals(data.getOperates())){
            //todo
            return true;
        }
        return nextExecutor.writeRequest(data);
    }

    @Override
    public RedisOperateData readRequest(RedisOperateData data) {
        if(RedisOperates.REDIS_KEYS_OPERATE.equals(data.getOperates())){
            //todo
            return data;
        }
        return nextExecutor.readRequest(data);
    }
}