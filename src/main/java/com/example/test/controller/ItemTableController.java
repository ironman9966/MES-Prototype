package com.example.test.controller;

import com.example.test.ItemTable;
import com.example.test.service.ItemTableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
