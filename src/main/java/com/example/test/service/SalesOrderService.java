package com.example.test.service;

import com.example.test.SalesOrder;
import com.example.test.repository.SalesOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class SalesOrderService {

    private final SalesOrderRepository salesOrderRepository;

    SalesOrderService(SalesOrderRepository repository) {
        this.salesOrderRepository = repository;
    }

    @GetMapping
    public List<SalesOrder> getAll(){
        return salesOrderRepository.findAll();
    }

    public Optional<SalesOrder> getByOrderNumber(String orderNumber){
        return salesOrderRepository.findBySalesOrderNumber(orderNumber);
    }

    public SalesOrder createSalesOrder(SalesOrder order){
        return salesOrderRepository.save(order);
    }

    public List<SalesOrder> getSalesOrderByStatus(SalesOrder.OrderStatus status){
        return salesOrderRepository.findByOrderStatus(status);
    }
}
