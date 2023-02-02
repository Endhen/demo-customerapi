package demo.springapi.customerapi.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;
    private final String exchange = "batch.exchange";
    private final String routingkey = "job.import";

    @Scheduled
    public void sendImportOrder(Integer orderId) {

        System.out.println("Send import order ...");
        rabbitTemplate.convertAndSend(exchange, routingkey, orderId);
    }

}
