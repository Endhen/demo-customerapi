package demo.springapi.customerapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.springapi.customerapi.entity.BatchOrder;
import demo.springapi.customerapi.repository.BatchOrderRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BatchOrderService implements BatchOrderServiceInterface {
    
    @Autowired
    private BatchOrderRepository repository;

    @Autowired
    private MessageSenderInterface rabbitMQSender;

    public Optional<BatchOrder> findById(long id) {
        return repository.findById(id);
    }

    public BatchOrder createNewBatchOrder() {

        log.info("Saving new order to data base");
        BatchOrder newOrder = new BatchOrder();
        log.debug("New order initial id " + newOrder.getId());
        BatchOrder batchOrder = repository.save(newOrder);
        log.debug("Save complete with id " + batchOrder.getId());
        
        log.info("Send new order to Rabbit MQ with id " + batchOrder.getId());
        rabbitMQSender.sendOrder(batchOrder.getId());
        return batchOrder;
    }

    public BatchOrder update(BatchOrder batchOrder) {
        return repository.save(batchOrder);
    }

}

