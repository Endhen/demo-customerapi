package demo.springapi.customerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.springapi.customerapi.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {}
