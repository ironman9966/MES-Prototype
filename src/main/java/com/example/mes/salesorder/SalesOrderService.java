package com.example.mes.salesorder;

import com.example.mes.exceptions.ItemNotFoundException;
import com.example.mes.itemtable.ItemTable;
import com.example.mes.itemtable.ItemTableRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesOrderService {

    private final SalesOrderRepository salesOrderRepository;
    private final ItemTableRepository itemTableRepository;

    SalesOrderService(SalesOrderRepository repository, ItemTableRepository itemTableRepository) {
        this.salesOrderRepository = repository;
        this.itemTableRepository = itemTableRepository;
    }

    public List<SalesOrderResponse> getAll(){
        return salesOrderRepository.findAll().stream().map(SalesOrderResponse::fromEntity).toList();
    }

    public Optional<SalesOrderResponse> getByOrderNumber(String orderNumber){
        return salesOrderRepository.findBySalesOrderNumber(orderNumber).map(SalesOrderResponse::fromEntity);
    }

    @Transactional
    public SalesOrderResponse createSalesOrder(SalesOrderRequest order){
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setSalesOrderNumber(order.orderNumber());
        salesOrder.setOrderStatus(order.status());

        for(SalesOrderRequest.SalesLineRequest line : order.salesLines()){
            ItemTable item = itemTableRepository.findByItemId(line.itemId())
                    .orElseThrow(() -> new ItemNotFoundException("Item not found:"+line.itemId()));
            SalesLine salesLine = new SalesLine();
            salesLine.setItemId(item);
            salesLine.setRequestDeliveryDate(line.deliveryDate());
            salesLine.setQuantity(line.quantity());
            salesOrder.addSalesLine(salesLine);
        }
        SalesOrder saved = salesOrderRepository.save(salesOrder);

        return SalesOrderResponse.fromEntity(saved);
    }

    public List<SalesOrderResponse> getSalesOrderByStatus(SalesOrder.OrderStatus status){
        List<SalesOrder> salesOrders = salesOrderRepository.findByOrderStatus(status);

        return salesOrders.stream().map(SalesOrderResponse::fromEntity).toList();
    }

    @Transactional
    public void deleteSalesOrder(String orderNumber){
        SalesOrder order = salesOrderRepository.findBySalesOrderNumber(orderNumber)
                .orElseThrow(() -> new ItemNotFoundException("Order not found:" + orderNumber));
        salesOrderRepository.delete(order);
    }

    @Transactional
    public SalesOrderResponse updateSalesOrder(SalesOrderRequest request){
        SalesOrder salesOrder = salesOrderRepository.findBySalesOrderNumber(request.orderNumber())
                .orElseThrow(() -> new ItemNotFoundException("Order not found:"+request.orderNumber()));
        salesOrder.setOrderStatus(request.status());

        return SalesOrderResponse.fromEntity(salesOrder);
    }
}
