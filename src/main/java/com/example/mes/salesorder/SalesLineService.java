package com.example.mes.salesorder;

import com.example.mes.itemtable.ItemTable;
import com.example.mes.exceptions.ItemNotFoundException;
import com.example.mes.itemtable.ItemTableService;
import org.springframework.stereotype.Service;

@Service
class SalesLineService {

    private final ItemTableService itemTableService;
    SalesLineRepository repository;

    SalesLineService(SalesLineRepository repository, ItemTableService itemTableService){
        this.repository = repository;
        this.itemTableService = itemTableService;
    }

    public SalesLine createSalesLine(SalesOrder order, SalesOrderRequest.SalesLineRequest request){
        ItemTable item = itemTableService.getItemId(request.itemId())
                .orElseThrow(() -> new ItemNotFoundException(request.itemId()));

        SalesLine line = new SalesLine();

        line.setItemId(item);
        line.setSalesOrder(order);
        line.setQuantity(request.quantity());

        return line;
    }
}
