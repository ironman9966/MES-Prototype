package com.example.mes.itemtable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RestController
@RequestMapping("/item-routing")
class ItemRoutingController {

    private final ItemRoutingService service;

    ItemRoutingController(ItemRoutingService service){
        this.service = service;
    }

    @GetMapping
    public List<ItemRouting> getAllItemRouting(){
        return service.getAllItemRouting();
    }

}
