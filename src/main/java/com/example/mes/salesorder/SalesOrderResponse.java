package com.example.mes.salesorder;

import java.util.List;
import java.time.LocalDate;

public record SalesOrderResponse(
        Long id,
        String orderNumber,
        SalesOrder.OrderStatus orderStatus,
        List<SalesLineResponse> lines) {

    public record SalesLineResponse(
            Long id,
            String orderNumber,
            Long quantitiy,
            String item,
            LocalDate requestDeliveryDate
    ){
    }

    public record SalesOrderUpdateResponse(
            Long id,
            String orderNumber,
            SalesOrder.OrderStatus orderStatus
    ){

    }

    public static SalesOrderResponse.SalesOrderUpdateResponse fromEntity(SalesOrder salesOrder){
        return new SalesOrderResponse.SalesOrderUpdateResponse(salesOrder.getId(),
                salesOrder.getSalesOrderNumber(),
                salesOrder.getOrderStatus());
    }
}
