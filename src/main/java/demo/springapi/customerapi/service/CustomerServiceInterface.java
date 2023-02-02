package demo.springapi.customerapi.service;

import demo.springapi.customerapi.entity.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerServiceInterface {
    public Optional<Customer> findById(long id);

    public List<Customer> findAll();

    public Customer create(Customer customer);

    public Customer update(Customer customer);

    public void delete(long id);

    public void saveAll(List<Customer> newCustomerList);
}
