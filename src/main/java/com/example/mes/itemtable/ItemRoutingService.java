package com.example.mes.itemtable;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemRoutingService {

    private final ItemRoutingRepository repository;

    ItemRoutingService(ItemRoutingRepository repository){
        this.repository = repository;
    }

    public List<ItemRouting> getAllItemRouting(){
        return repository.findAll();
    }
}
