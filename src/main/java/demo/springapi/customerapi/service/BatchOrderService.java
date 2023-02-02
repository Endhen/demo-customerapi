package demo.springapi.customerapi.service;

import java.util.List;
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

    public List<BatchOrder> findAll() {
        return repository.findAll();
    }

    public BatchOrder createNewBatchOrder() {

        log.info("Saving new order to data base");
        BatchOrder newOrder = new BatchOrder();
        repository.save(newOrder);
        log.debug("Save complete with id " + newOrder.getId());
        
        log.info("Send new order to Rabbit MQ with id " + newOrder.getId());
        rabbitMQSender.sendOrder(newOrder.getId());
        return newOrder;
    }

    public BatchOrder update(BatchOrder customer) {
        return repository.save(customer);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

}

