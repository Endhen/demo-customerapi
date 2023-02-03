package demo.springapi.customerapi.service;

import java.util.Optional;

import demo.springapi.customerapi.entity.BatchOrder;

public interface BatchOrderServiceInterface {

    public Optional<BatchOrder> findById(long id);

    public BatchOrder createNewBatchOrder();

    public BatchOrder update(BatchOrder batchOrder);

}
