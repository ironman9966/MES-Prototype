package com.example.mes.itemtable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<ItemRouting> createItemRouting(@RequestBody ItemRoutingRequest request){
        ItemRouting saved = service.createItemRouting(request);
        URI location = URI.create("item-routing" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

}
