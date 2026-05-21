package com.demo.namartejshop.repository;

import com.demo.namartejshop.model.Employee;
import com.demo.namartejshop.model.Order;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser_IdOrderByFechaDesc(Long id);

}
