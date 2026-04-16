package com.demo.namartejshop.repository;

import com.demo.namartejshop.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {


}