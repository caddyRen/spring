package indi.ikun.spring.demospringboot.config.rabbitmq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 自动创建消息队列
 */
@Component
@ConditionalOnProperty(prefix = "rabbitmq", name = "enable",havingValue = "true")
public class InitRabbitMQ2 {
    /**
     * 自动创建队列方式2，Exchange 与 Queue绑定
     */
    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue("queue"),
                            exchange = @Exchange(type = ExchangeTypes.TOPIC, value = "exchange"),
                            key = "key1"
                    ),
                    @QueueBinding(
                            value = @Queue("queue2"),
                            exchange = @Exchange(type = ExchangeTypes.TOPIC, value = "exchange2"),
                            key = "key2"
                    )
            }

    )
    public void initmq() {
    }
}
