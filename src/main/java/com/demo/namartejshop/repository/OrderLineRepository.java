package com.demo.namartejshop.repository;

import com.demo.namartejshop.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
//    @Query("""
//        SELECT SUM(ol.quantity * ol.dish.price)
//        FROM
//""")
//    Double calculateTotalPrice(Long orderId);


}