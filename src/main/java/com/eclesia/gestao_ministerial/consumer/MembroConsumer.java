package com.eclesia.gestao_ministerial.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MembroConsumer {

    @RabbitListener(queues = "${rabbitmq.queue.auth-events}")
    public void receberLogin(Map<String, Object> event) {
        System.out.println("🔥 Evento recebido do login-ms:");
        System.out.println(event);
    }
}
