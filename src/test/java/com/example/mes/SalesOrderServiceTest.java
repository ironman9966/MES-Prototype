package com.example.mes;

import com.example.mes.salesorder.SalesOrder;
import com.example.mes.salesorder.SalesOrderRepository;
import com.example.mes.salesorder.SalesOrderResponse;
import com.example.mes.salesorder.SalesOrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SalesOrderServiceTest {

    @Mock
    private SalesOrderRepository repository;

    @InjectMocks
    private SalesOrderService service;

    @Test
    void getSalesOrderById_ReturnOrder_WhenOrderExists(){
        SalesOrder mockOrder = new SalesOrder();
        mockOrder.setSalesOrderNumber("20260908001");
        mockOrder.setId(1L);

        Mockito.when(repository.findBySalesOrderNumber("20260908001")).thenReturn(Optional.of(mockOrder));

        Optional<SalesOrderResponse> order = service.getByOrderNumber("20260908001");

        Assertions.assertThat(order).isPresent().hasValueSatisfying(salesOrderResponse ->
                Assertions.assertThat(salesOrderResponse.id()).isEqualTo(1L));
    }
}
