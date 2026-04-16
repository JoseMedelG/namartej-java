package com.demo.namartejshop.repository;

import com.demo.namartejshop.model.Employee;
import com.demo.namartejshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
