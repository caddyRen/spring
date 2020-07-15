package indi.ikun.spring.demospringboot.config.rabbitmq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "rabbitmq", name = "enable",havingValue = "true")
public class RabbitMQ {

    @Value("${rabbitmq.queue}")
    String queue;

    @Value("${rabbitmq.exchange}")
    String exchange;

    @Value("${rabbitmq.routkey}")
    String routkey;

}
