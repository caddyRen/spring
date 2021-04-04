package org.bougainvillea.spring.redisdepency.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Objects;

@Configuration
@EnableCaching
//@RefreshScope
public class RedisConfig extends CachingConfigurerSupport {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.lettuce.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.lettuce.pool.max-wait}")
    private int maxWait;
    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int minIdle;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        config.setPassword(RedisPassword.of(password));
        JedisConnectionFactory factory = new JedisConnectionFactory(config);
        System.err.println(factory.getTimeout());
        Objects.requireNonNull(factory.getPoolConfig()).setMaxIdle(maxIdle);
        Objects.requireNonNull(factory.getPoolConfig()).setMinIdle(minIdle);
        Objects.requireNonNull(factory.getPoolConfig()).setMaxTotal(maxActive);
        Objects.requireNonNull(factory.getPoolConfig()).setMaxWaitMillis(maxWait);
        return factory;
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> template = new RedisTemplate <>();
        template.setConnectionFactory(jedisConnectionFactory());
        // key采用String的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        // value序列化方式采用jackson
        template.setValueSerializer(getJackson2JsonRedisSerializer());
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(getJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate strRedisTemplate=new StringRedisTemplate(jedisConnectionFactory());
        // key采用String的序列化方式
        strRedisTemplate.setKeySerializer(new StringRedisSerializer());
        // hash的key也采用String的序列化方式
        strRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // value序列化方式采用jackson
        strRedisTemplate.setValueSerializer(getJackson2JsonRedisSerializer());
        // hash的value序列化方式采用jackson
        strRedisTemplate.setHashValueSerializer(getJackson2JsonRedisSerializer());
        return strRedisTemplate;
    }

    private Jackson2JsonRedisSerializer<Object> getJackson2JsonRedisSerializer(){
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }


}

