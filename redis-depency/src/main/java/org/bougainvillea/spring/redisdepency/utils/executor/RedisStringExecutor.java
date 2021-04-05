package org.bougainvillea.spring.redisdepency.utils.executor;

import lombok.extern.slf4j.Slf4j;
import org.bougainvillea.spring.redisdepency.utils.RedisOperateData;
import org.bougainvillea.spring.redisdepency.utils.RedisOperates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * redis String 数据操作
 * @author caddy
 */
@Component
@Slf4j
public class RedisStringExecutor extends RedisExecutor {

    @Autowired
    public RedisStringExecutor(RedisTemplate<String, Object> redisTemplate) {
     super(redisTemplate);
    }

    @Override
    public boolean writeRequest(RedisOperateData data) {
        if(RedisOperates.REDIS_STRING_OPERATE.equals(data.getOperates())){
            boolean result = false;
            try {
                ValueOperations<String, Object> operations = redisTemplate.opsForValue();
                operations.set(data.getKey(), data.getObject());
                result = true;
            } catch (Exception e) {
                log.error("往redis写入数据失败:{ }",e);
            }
            return result;
        }
        return nextExecutor.writeRequest(data);
    }

    @Override
    public RedisOperateData readRequest(RedisOperateData data) {
        if(RedisOperates.REDIS_STRING_OPERATE.equals(data.getOperates())){
            try{
                ValueOperations<String, Object> operations = redisTemplate.opsForValue();
                data.setObject(operations.get(data.getKey()));
            }catch (Exception e){
                log.error("从redis读取数据失败:{}",e);
            }
            return data;
        }
        return nextExecutor.readRequest(data);
    }
}