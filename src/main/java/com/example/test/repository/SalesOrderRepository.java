package com.example.test.repository;

import com.example.test.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {
    Optional<SalesOrder> findBySalesOrderNumber(String orderNumber);

    List<SalesOrder> findByOrderStatus(SalesOrder.OrderStatus status);
}
