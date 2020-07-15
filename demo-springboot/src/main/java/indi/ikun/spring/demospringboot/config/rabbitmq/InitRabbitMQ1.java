package indi.ikun.spring.demospringboot.config.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * 自动创建消息队列
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "rabbitmq", name = "enable", havingValue = "true")
public class InitRabbitMQ1 {

    private RabbitMQ rabbitMQ;

    @Autowired
    public InitRabbitMQ1(
            RabbitMQ rabbitMQ
    ) {
        Assert.notNull(rabbitMQ, "rabbitMQ must not be null");
        this.rabbitMQ = rabbitMQ;
    }

    @Bean
    public TopicExchange exchange() {
        log.info("==> Create a rabbitmq exchange [{}]", rabbitMQ.exchange);
        return new TopicExchange(rabbitMQ.exchange);
    }

    @Bean
    public Queue instantQueue() {
        log.info("==> Create a rabbitmq queue [{}]", rabbitMQ.queue);
        return new Queue(rabbitMQ.queue, true);
    }

    @Bean
    public Binding binding() {
        log.info("==> Binding the rabbitmq queue [{}] to exchange [{}]", rabbitMQ.queue, rabbitMQ.exchange);
        return BindingBuilder.bind(instantQueue())
                .to(exchange())
                .with(rabbitMQ.routkey);
    }

}
