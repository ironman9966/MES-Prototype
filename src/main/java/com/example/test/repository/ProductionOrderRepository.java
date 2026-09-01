package com.example.test.repository;

import com.example.test.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, Long>{
    Optional<ProductionOrder> findByOrderNumber(String orderNumber);
}
