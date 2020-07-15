package indi.ikun.spring.demospringboot.config.rabbitmq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


/**
 * AmqpAdmin 方式自动创建queue exchange binding
 * 此方式在华为云cse模式下无效
 */
@ConditionalOnProperty(prefix = "rabbitmq", name = "enable",havingValue = "true")
@Component
@Slf4j
public class InitRabbitMQ {
    @Autowired
    public InitRabbitMQ(AmqpAdmin amqpAdmin, RabbitMQ rabbitMQ) {
        Assert.notNull(amqpAdmin, "amqpAdmin must not be null");
        Assert.notNull(rabbitMQ, "rabbitMQ must not be null");

        amqpAdmin.deleteExchange(rabbitMQ.exchange);
        log.info("======> DELETE EXCHANGE [{}]", rabbitMQ.exchange);
        amqpAdmin.deleteQueue(rabbitMQ.queue);
        log.info("======> DELETE QUEUE [{}]", rabbitMQ.queue);
        amqpAdmin.declareExchange(new TopicExchange(rabbitMQ.exchange));
        //创建queue
        amqpAdmin.declareQueue(new Queue(rabbitMQ.queue, true));
        //创建binding
        amqpAdmin.declareBinding(
                new Binding(
                        rabbitMQ.queue,
                        Binding.DestinationType.QUEUE,
                        rabbitMQ.exchange,
                        rabbitMQ.routkey,
                        null
                )
        );
        log.info("CREATE EXCHANGE [{}] Binding QUEUE [{}] and routingKey is [{}]", rabbitMQ.exchange, rabbitMQ.queue, rabbitMQ.routkey);
    }
}
