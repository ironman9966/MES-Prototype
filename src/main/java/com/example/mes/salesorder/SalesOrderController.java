package com.example.mes.salesorder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<SalesOrder> create(@RequestBody SalesOrder order){
        SalesOrder saved = service.createSalesOrder(order);
        URI location = URI.create("/sales-order/" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }
}
