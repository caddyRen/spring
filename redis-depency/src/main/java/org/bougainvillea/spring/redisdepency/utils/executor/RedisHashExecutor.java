package org.bougainvillea.spring.redisdepency.utils.executor;

import org.bougainvillea.spring.redisdepency.utils.RedisOperateData;
import org.bougainvillea.spring.redisdepency.utils.RedisOperates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis hash 数据操作
 * @author caddy
 */
@Component
public class RedisHashExecutor extends RedisExecutor{

    @Autowired
    public RedisHashExecutor(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public boolean writeRequest(RedisOperateData data) {
        if(RedisOperates.REDIS_HASH_OPERATE.equals(data.getOperates())){
            //todo
            return true;
        }
        return nextExecutor.writeRequest(data);
    }

    @Override
    public RedisOperateData readRequest(RedisOperateData data) {
        if(RedisOperates.REDIS_HASH_OPERATE.equals(data.getOperates())){
            //todo
            return data;
        }
        return nextExecutor.readRequest(data);
    }

}