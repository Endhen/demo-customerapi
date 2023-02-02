package demo.springapi.customerapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.springapi.customerapi.entity.BatchOrder;
import demo.springapi.customerapi.service.BatchOrderService;

@RestController
@RequestMapping("/batch-order")
public class BatchOrderController {
    
    @Autowired
    private BatchOrderService service;

    @PostMapping
    public ResponseEntity<BatchOrder> create(@RequestBody BatchOrder newBatchOrder){
        
        newBatchOrder = new BatchOrder();

        return ResponseEntity.ok(service.create(newBatchOrder));
    }

    @GetMapping(path="/{id}")
    private ResponseEntity<BatchOrder> getBatchOrder(@PathVariable int id) {
        Optional<BatchOrder> batchOrder = service.findById(id);

        if(batchOrder.isPresent())
            return ResponseEntity.ok(batchOrder.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<BatchOrder>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    
    @PutMapping(path="/{id}")
    public ResponseEntity<BatchOrder> update(@PathVariable int id, @RequestBody BatchOrder updatedBatchOrder){
        updatedBatchOrder.setId(id);
        
        return ResponseEntity.ok(service.update(updatedBatchOrder));
    }
    
    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        service.delete(id);

        return ResponseEntity.ok("DELETED");
    }

}
