package com.example.test.controller;

import com.example.test.SalesOrder;
import com.example.test.service.SalesOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sales-order")
class SalesOrderController {

    private final SalesOrderService service;

    SalesOrderController(SalesOrderService service){
        this.service = service;
    }

    @GetMapping
    public List<SalesOrder> getAllSalesOrder(){
        return service.getAll();
    }

    @GetMapping("/{orderNumber}")
    public Optional<SalesOrder> getBySalesOrderNumber(@PathVariable String orderNumber){
        return service.getByOrderNumber(orderNumber);
    }

    @GetMapping
    public List<SalesOrder> getByOrderStatus(@RequestParam SalesOrder.OrderStatus status){
        return service.getSalesOrderByStatus(status);
    }

}
