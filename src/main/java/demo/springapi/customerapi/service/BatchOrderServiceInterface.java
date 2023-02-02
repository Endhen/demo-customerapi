package demo.springapi.customerapi.service;

import demo.springapi.customerapi.entity.BatchOrder;
import java.util.List;
import java.util.Optional;

public interface BatchOrderServiceInterface {

    public Optional<BatchOrder> findById(long id);

    public List<BatchOrder> findAll();

    public BatchOrder createNewBatchOrder();

    public BatchOrder update(BatchOrder customer);

    public void delete(long id);
}
