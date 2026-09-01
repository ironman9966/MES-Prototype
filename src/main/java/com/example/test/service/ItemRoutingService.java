package com.example.test.service;

import com.example.test.ItemRouting;
import com.example.test.repository.ItemRoutingRepository;
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
