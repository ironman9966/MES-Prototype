package com.example.mes.productionorder;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/production-orders")
public class ProductionOrderController {

    private final ProductionOrderService service;

    public ProductionOrderController(ProductionOrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductionOrder> create(@RequestBody ProductionOrder order) {
        ProductionOrder saved = service.createOrder(order);
        URI location = URI.create("/production-orders/" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping
    public List<ProductionOrder> getAll() {
        return service.getAll();
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<ProductionOrder> getById(@PathVariable String orderNumber) {
        return service.findByOrderNumber(orderNumber)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
