package demo.springapi.customerapi.service;

import java.util.List;

import demo.springapi.customerapi.entity.Customer;

public interface CustomerServiceInterface {

    public List<Customer> saveAll(List<Customer> newCustomerList);

}
