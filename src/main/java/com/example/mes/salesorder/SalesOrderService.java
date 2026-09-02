package com.example.mes.salesorder;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class SalesOrderService {

    private final SalesOrderRepository salesOrderRepository;
    private final SalesLineService salesLineService;

    SalesOrderService(SalesOrderRepository repository, SalesLineService salesLineService) {
        this.salesOrderRepository = repository;
        this.salesLineService = salesLineService;
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
