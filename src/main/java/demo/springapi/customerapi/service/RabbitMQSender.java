package demo.springapi.customerapi.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;
 
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
 
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

 
    @Scheduled(fixedRate = 10_000)
    public void send(String message) {

        rabbitTemplate.convertAndSend(exchange, routingkey, message);
        System.out.println("Send msg to customer batch : " + message);
    }

    @Scheduled
    public Object sendAndReceive(String message) {

        System.out.println("Send msg to customer batch : " + message);
        return rabbitTemplate.convertSendAndReceive(exchange, routingkey, message);
    }
}
