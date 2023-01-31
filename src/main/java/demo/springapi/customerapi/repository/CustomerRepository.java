package demo.springapi.customerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.springapi.customerapi.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {}
