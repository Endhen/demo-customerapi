package demo.springapi.customerapi.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.springapi.customerapi.entity.Customer;
import demo.springapi.customerapi.service.CustomerService;
import demo.springapi.customerapi.service.RabbitMQSender;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
    private CustomerService service;

    @Autowired
    private RabbitMQSender rabbitMQSender;    

    @PostMapping(value = "/sendMessage")
    public String producer(@RequestParam(value="message") String message) throws IOException { 
 
        rabbitMQSender.send(message);
        return "Message sent to the RabbitMQ Successfully";
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer newCustomer){
        return ResponseEntity.ok(service.create(newCustomer));
    }

    @GetMapping(path="/{id}")
    private ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        Optional<Customer> customer = service.findById(id);

        if(customer.isPresent())
            return ResponseEntity.ok(customer.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    
    @PutMapping(path="/{id}")
    public ResponseEntity<Customer> update(@PathVariable int id, @RequestBody Customer updatedCustomer){
        updatedCustomer.setId(id);
        
        return ResponseEntity.ok(service.update(updatedCustomer));
    }
    
    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        service.delete(id);

        return ResponseEntity.ok("DELETED");
    }

}
