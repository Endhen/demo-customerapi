package demo.springapi.customerapi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.springapi.customerapi.entity.Customer;
import demo.springapi.customerapi.service.CustomerServiceInterface;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
    private CustomerServiceInterface customerService; 

    @PostMapping(value = "/list")
    public ResponseEntity<String> importCustomerList(@RequestBody List<Customer> newCustomerList) throws IOException {

        customerService.saveAll(newCustomerList); 

        return new ResponseEntity<>(
            newCustomerList.size() + "customers successfuly imported into data base", 
            HttpStatus.CREATED
        );
    }

}
