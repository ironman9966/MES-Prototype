package com.example.mes.salesorder;

import java.time.LocalDate;
import java.util.List;

public record SalesOrderRequest(SalesOrder.OrderStatus status, String orderNumber, List<SalesLineRequest> salesLines) {
    public record SalesLineRequest(String itemId, Long quantity, LocalDate deliveryDate){
    }
}
