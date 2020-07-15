package indi.ikun.spring.demospringboot.config.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName MyRabbitMQConfigurer
 * @Description RabbitMQ消息队列配置主要涉及发布确认
 * @Author caddyR
 * @Date 2019-03-25 17:15
 * @Version 1.0
 **/
@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "rabbitmq", name = "enable",havingValue = "true")
public class MyRabbitMQConfigurer {

    @Autowired
    @Bean("myRabbitContainerFactory")
    public SimpleRabbitListenerContainerFactory myRabbitContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer simpleRabbitListenerContainerFactoryConfigurer,ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory=new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        /**
         * 序列化
         */
        simpleRabbitListenerContainerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
        //simpleRabbitListenerContainerFactory.setConcurrentConsumers(2);//初始化线程数，消费者会自动多线程消费
        //simpleRabbitListenerContainerFactory.setMaxConcurrentConsumers(10);//最大线程数，当获取的消息达到10时，且未确认，就不会获取消息了
        //simpleRabbitListenerContainerFactory.setPrefetchCount(5);//设置预取计数
        //simpleRabbitListenerContainerFactory.setTxSize(1);//事务数 不知都具体作用
        //simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);//开启手动确认,接收消息后手动确认消息，配置文件内可以配置
        simpleRabbitListenerContainerFactoryConfigurer.configure(simpleRabbitListenerContainerFactory,connectionFactory);
        return simpleRabbitListenerContainerFactory;
    }

    /**
     * 多个消费者
     * @return
     */

    @Autowired
    @Bean("myRabbitTemplate")
    public RabbitTemplate myRabbitTemplate(CachingConnectionFactory cachingConnectionFactory){
//        cachingConnectionFactory.setPublisherConfirms(true);//配置文件可以设置
//        cachingConnectionFactory.setPublisherReturns(true);//配置文件可以设置
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMandatory(true);
        //序列化设置需要与SimpleRabbitListenerContainerFactory配置一致，不然无法解析消息内容
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        //发布确认模式
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(ack){
                    log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
                }else {
                    log.info("消息发送失败:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
                }
            }
        });


        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {

            /**
             *@Author caddyR
             *@Description //找不到队列消息，消息将丢失
             *@Date 2019-04-11 12:30
             *@Param [message, replyCode, replyText, exchange, routingKey]
             *@return void
            **/
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
            }
        });
        return rabbitTemplate;
    }
}