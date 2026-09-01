package com.example.test.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.test.ProductionOrderStatus;
import com.example.test.ProductionOrder;
import com.example.test.repository.ProductionOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductionOrderService {
    
    private final ProductionOrderRepository repository;

    ProductionOrderService(ProductionOrderRepository repository) {
        this.repository = repository;
    }

    public ProductionOrder createOrder(ProductionOrder order) {
        if(order.getStatus() == null){
            order.setStatus(ProductionOrderStatus.Open);
        }
        if(order.getInputDate() == null){
            order.setInputDate(LocalDateTime.now());
        }
        return repository.save(order);
    }

    public List<ProductionOrder> getAll(){
        return repository.findAll();
    }

    public Optional<ProductionOrder> findByOrderNumber(String orderNumber){
        return repository.findByOrderNumber(orderNumber);
    }
}
