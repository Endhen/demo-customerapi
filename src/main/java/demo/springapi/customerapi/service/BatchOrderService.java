package demo.springapi.customerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.springapi.customerapi.entity.BatchOrder;
import demo.springapi.customerapi.repository.BatchOrderRepository;

@Service
public class BatchOrderService {
    
    @Autowired
    private BatchOrderRepository repository;

    public Optional<BatchOrder> findById(int id) {
        return repository.findById(id);
    }

    public List<BatchOrder> findAll() {
        return repository.findAll();
    }

    public BatchOrder create(BatchOrder customer) {
        return repository.save(customer);
    }

    public BatchOrder update(BatchOrder customer) {
        return repository.save(customer);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

}

