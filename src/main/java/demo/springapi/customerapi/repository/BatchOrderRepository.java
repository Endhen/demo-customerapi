package demo.springapi.customerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.springapi.customerapi.entity.BatchOrder;

public interface BatchOrderRepository extends JpaRepository<BatchOrder, Integer> {}
