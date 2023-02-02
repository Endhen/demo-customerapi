package demo.springapi.customerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.springapi.customerapi.entity.Customer;
import demo.springapi.customerapi.repository.CustomerRepository;

@Service
public class CustomerService implements CustomerServiceInterface {
    
    @Autowired
    private CustomerRepository repository;

    public Optional<Customer> findById(long id) {
        return repository.findById(id);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    public Customer update(Customer customer) {
        return repository.save(customer);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public void saveAll(List<Customer> newCustomerList) {
        repository.saveAll(newCustomerList);
    }
}

