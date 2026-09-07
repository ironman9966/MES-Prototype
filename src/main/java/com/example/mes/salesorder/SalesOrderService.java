package com.example.mes.salesorder;

import com.example.mes.exceptions.ItemNotFoundException;
import com.example.mes.itemtable.ItemTable;
import com.example.mes.itemtable.ItemTableRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping
    public List<SalesOrder> getAll(){
        return salesOrderRepository.findAll();
    }

    public Optional<SalesOrder> getByOrderNumber(String orderNumber){
        return salesOrderRepository.findBySalesOrderNumber(orderNumber);
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

        List<SalesOrderResponse.SalesLineResponse> lineResponses = saved.getSalesLines().stream()
                .map((SalesLine line) -> new SalesOrderResponse.SalesLineResponse(
                        line.getId(),
                        line.getSalesOrder().getSalesOrderNumber(),
                        line.getQuantity(),
                        line.getItemId().getItemId(),
                        line.getRequestDeliveryDate()
                )).toList();

        return new SalesOrderResponse(
                saved.getId(),
                saved.getSalesOrderNumber(),
                saved.getOrderStatus(),
                lineResponses);
    }

    public List<SalesOrder> getSalesOrderByStatus(SalesOrder.OrderStatus status){
        return salesOrderRepository.findByOrderStatus(status);
    }

    @Transactional
    public void deleteSalesOrder(String orderNumber){
        SalesOrder order = salesOrderRepository.findBySalesOrderNumber(orderNumber)
                .orElseThrow(() -> new ItemNotFoundException("Order not found:" + orderNumber));
        salesOrderRepository.delete(order);
    }

    @Transactional
    public SalesOrderResponse.SalesOrderUpdateResponse updateSalesOrder(SalesOrderRequest request){
        SalesOrder salesOrder = salesOrderRepository.findBySalesOrderNumber(request.orderNumber())
                .orElseThrow(() -> new ItemNotFoundException("Order not found:"+request.orderNumber()));
        salesOrder.setOrderStatus(request.status());

        return SalesOrderResponse.fromEntity(salesOrder);
    }
}
