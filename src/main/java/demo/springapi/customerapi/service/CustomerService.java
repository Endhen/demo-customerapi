package demo.springapi.customerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.springapi.customerapi.entity.Customer;
import demo.springapi.customerapi.repository.CustomerRepository;

@Service
public class CustomerService implements CustomerServiceInterface {
    
    @Autowired
    private CustomerRepository repository;

    public List<Customer> saveAll(List<Customer> newCustomerList) {
        return repository.saveAll(newCustomerList);
    }

}

