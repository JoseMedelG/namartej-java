package com.demo.namartejshop.repository;

import com.demo.namartejshop.model.Employee;
import com.demo.namartejshop.model.Order;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser_IdOrderByFechaDesc(Long id);

    Long countByUser_Id(Long id);

    @Query("""
          SELECT COALESCE(SUM(o.totalPrice), 0.0) from Order o where o.user.id = :userId
""")
    double calculateTotalMoneySpentByUserId(Long userId);


}
