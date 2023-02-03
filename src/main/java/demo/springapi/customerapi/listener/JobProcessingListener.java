package demo.springapi.customerapi.listener;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.springapi.customerapi.entity.BatchOrder;
import demo.springapi.customerapi.enums.OrderStatus;
import demo.springapi.customerapi.service.BatchOrderService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RabbitListener(queues = "processing-queue")
public class JobProcessingListener {

    @Autowired
    private BatchOrderService batchOrderService;

    @RabbitHandler
    public void process(long orderId) {

        Optional<BatchOrder> batchOrder = batchOrderService.findById(orderId);
        
        if (batchOrder.isPresent()) 
            batchOrderService.update(
                batchOrder.get().setStatus(OrderStatus.PROCESSING));
        else 
            log.error("[PROCESSING-QUEUE] Batch order lost");
        
    }

}
