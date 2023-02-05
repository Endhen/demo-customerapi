package demo.springapi.customerapi.service;

import org.springframework.amqp.AmqpException;

public interface MessageSenderInterface {
    public void sendOrder(long orderId) throws AmqpException;
}
