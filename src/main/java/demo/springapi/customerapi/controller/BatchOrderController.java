package demo.springapi.customerapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.springapi.customerapi.entity.BatchOrder;
import demo.springapi.customerapi.service.BatchOrderServiceInterface;

@RestController
@RequestMapping("/batch-order")
public class BatchOrderController {
    
    @Autowired
    private BatchOrderServiceInterface service;

    @PostMapping(value = "/generate")
    public ResponseEntity<String> create(){
        
        BatchOrder newOrder = service.createNewBatchOrder();
        
        return new ResponseEntity<>(
            "New import order successfully created with id " + newOrder.getId(), 
            HttpStatus.ACCEPTED
        );
    }

    @GetMapping(path="/{id}")
    private ResponseEntity<BatchOrder> getBatchOrder(@PathVariable int id) {
        Optional<BatchOrder> batchOrder = service.findById(id);

        if(batchOrder.isPresent())
            return ResponseEntity.ok(batchOrder.get());
        else
            return ResponseEntity.notFound().build();
    }

}
