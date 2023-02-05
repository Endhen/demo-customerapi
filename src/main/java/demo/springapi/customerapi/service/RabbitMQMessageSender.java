package demo.springapi.customerapi.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class RabbitMQMessageSender implements MessageSenderInterface {

    @Autowired
    private AmqpTemplate rabbitTemplate;
    private final String exchange = "batch-exchange.direct";
    private final String routingkey = "job.start";

    public void sendOrder(long orderId) throws AmqpException {

        log.info(
            "Sending order with routingkey " + routingkey +  
            " and id " + orderId + "..."
        );
        rabbitTemplate.convertAndSend(exchange, routingkey, orderId);
    }

}
