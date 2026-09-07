package com.example.mes.itemtable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("item-table")
class ItemTableController {

    private final ItemTableService service;

    ItemTableController(ItemTableService service){
        this.service = service;
    }

    @GetMapping
    public List<ItemTable> getAllItemTable(){
        return service.getAllItemTable();
    }

    @GetMapping("/{item_id}")
    public Optional<ItemTable> getByItemId(@PathVariable String item_id){
        return service.getItemId(item_id);
    }

    @PostMapping
    public ResponseEntity<ItemTable> createItem(@RequestBody ItemTable item){
        ItemTable saved = service.createItem(item);
        URI location = URI.create("item-table" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

}
