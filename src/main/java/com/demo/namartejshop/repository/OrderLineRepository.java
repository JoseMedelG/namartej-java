package com.demo.namartejshop.repository;

import com.demo.namartejshop.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
   @Query("""
        SELECT SUM(ol.quantity * ol.productos.price)
        FROM OrderLine ol where ol.order.id = ?1
       """)
    Double calculateTotalPrice(Long orderId);
}