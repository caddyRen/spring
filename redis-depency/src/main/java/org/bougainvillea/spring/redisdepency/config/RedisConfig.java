package org.bougainvillea.spring.redisdepency.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    private RedisProperties redisProperties;

    @Autowired
    public RedisConfig(RedisProperties redisProperties) {
        Assert.notNull(redisProperties, "redisProperties must be not null");
        this.redisProperties = redisProperties;
    }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        // 配置用于开启自适应刷新和定时刷新。如自适应刷新不开启，Redis集群变更时将会导致连接异常
        ClusterTopologyRefreshOptions clusterTopologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                .enableAllAdaptiveRefreshTriggers()
                .enablePeriodicRefresh(Duration.ofSeconds(10))// 开启周期刷新(默认60秒)
                .enableAdaptiveRefreshTrigger(ClusterTopologyRefreshOptions.RefreshTrigger.ASK_REDIRECT, ClusterTopologyRefreshOptions.RefreshTrigger.UNKNOWN_NODE)// 开启自适应刷新
                .build();

        ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
                .topologyRefreshOptions(clusterTopologyRefreshOptions)//拓扑刷新
                .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
                .autoReconnect(true)
                .socketOptions(SocketOptions.builder().keepAlive(true).build()).validateClusterNodeMembership(false)// 取消校验集群节点的成员关系
                .build();

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .clientOptions(clusterClientOptions)
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                .build();

        RedisClusterConfiguration configuration = new RedisClusterConfiguration(redisProperties.getCluster().getNodes());
        configuration.setMaxRedirects(redisProperties.getCluster().getMaxRedirects());
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration, clientConfig);
        factory.setDatabase(0);
        factory.setShareNativeConnection(true);
        factory.resetConnection();
        return factory;
    }


    /**
     * key string
     * value Object
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
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

    /**
     * key value 都是string
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        StringRedisTemplate strRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
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

    /**
     * json序列化
     */
    private Jackson2JsonRedisSerializer<Object> getJackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(om.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }


}

