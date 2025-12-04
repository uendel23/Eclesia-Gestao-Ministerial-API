package com.eclesia.gestao_ministerial.config;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.queue.auth-events}")
    private String authEventsQueue;

    @Bean
    public Queue authQueue() {
        return new Queue(authEventsQueue, true);
    }
}
