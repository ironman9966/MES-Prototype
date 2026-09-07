package com.example.mes.salesorder;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SalesLineRequest {
    String itemId;
    private Integer quantity;
    private LocalDate requestDeliveryDate;
}
