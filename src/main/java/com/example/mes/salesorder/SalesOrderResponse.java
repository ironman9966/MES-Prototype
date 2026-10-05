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
            Long quantity,
            String item,
            LocalDate requestDeliveryDate
    ){
        public static SalesLineResponse fromEntity(SalesLine line) {
            return new SalesLineResponse(
                    line.getId(),
                    line.getSalesOrder().getSalesOrderNumber(),
                    line.getQuantity(),
                    line.getItemId().getItemId(),
                    line.getRequestDeliveryDate()
            );
        }
    }

    public static SalesOrderResponse fromEntity(SalesOrder salesOrder){
        List<SalesLineResponse> salesLineResponses = salesOrder.getSalesLines().stream().map(
                SalesLineResponse::fromEntity).toList();

        return new SalesOrderResponse(salesOrder.getId(),
                salesOrder.getSalesOrderNumber(),
                salesOrder.getOrderStatus(),
                salesLineResponses
                );
    }

}
