package demo.springapi.customerapi.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "success-queue")
public class JobFinishedListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final String exchange = "amq.topic";
    private final String routingkey = "job.import";

    @RabbitHandler
    public void process(String message) {

        System.out.println("LISTENER : " + message);

        // TODO Change request status

        // TODO Create the response message
        // Message responseMessage = MessageBuilder
        //         .withBody("Batch response".getBytes())
        //         .build();

        // Send the response message
        // rabbitTemplate.convertSendAndReceive(exchange, routingkey, "responseMessage");
    }

}
