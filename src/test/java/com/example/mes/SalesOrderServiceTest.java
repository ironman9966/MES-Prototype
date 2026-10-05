package com.example.mes;

import com.example.mes.itemtable.ItemTable;
import com.example.mes.itemtable.ItemTableRepository;
import com.example.mes.salesorder.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SalesOrderServiceTest {

    @Mock
    private SalesOrderRepository repository;

    @Mock
    private ItemTableRepository itemTableRepository;

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

    @Test
    void createSalesOrder_ReturnOrder_WhenOrdersCreates(){

        SalesOrderRequest.SalesLineRequest lineRequest = new SalesOrderRequest.SalesLineRequest("1000", 1L, LocalDate.parse("2026-09-10"));

        SalesOrderRequest request = new SalesOrderRequest(SalesOrder.OrderStatus.Open, "20260910001", List.of(lineRequest));

        ItemTable mockItem = new ItemTable();
        mockItem.setItemId("1000");
        mockItem.setId(101L);

        SalesOrder mockOrder = new SalesOrder();
        mockOrder.setSalesOrderNumber(request.orderNumber());

        Mockito.when(itemTableRepository.findByItemId("1000")).thenReturn(Optional.of(mockItem));

        Mockito.when(repository.save(Mockito.any(SalesOrder.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SalesOrderResponse orderResponse = service.createSalesOrder(request);

        Mockito.verify(repository).save(Mockito.any(SalesOrder.class));

        ArgumentCaptor<SalesOrder> captor =
                ArgumentCaptor.forClass(SalesOrder.class);

        Mockito.verify(repository).save(captor.capture());

        SalesOrder createdOrder = captor.getValue();
        Assertions.assertThat(createdOrder.getSalesOrderNumber())
                .isEqualTo("20260910001");

        Assertions.assertThat(createdOrder.getOrderStatus())
                .isEqualTo(SalesOrder.OrderStatus.Open);

        Assertions.assertThat(createdOrder.getSalesLines())
                .hasSize(1);

        SalesLine createdLine = createdOrder.getSalesLines().getFirst();

        Assertions.assertThat(createdLine.getItemId())
                .isEqualTo(mockItem);

        Assertions.assertThat(createdLine.getQuantity())
                .isEqualTo(1L);

        Assertions.assertThat(createdLine.getRequestDeliveryDate())
                .isEqualTo(LocalDate.of(2026, 9, 10));

    }
}
