package com.example.test.service;

import java.util.List;
import java.util.Optional;

import com.example.test.ItemTable;
import com.example.test.repository.ItemTableRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemTableService {

    private final ItemTableRepository repository;

    ItemTableService(ItemTableRepository repository){
        this.repository = repository;
    }

    public List<ItemTable> getAllItemTable(){
        return repository.findAll();
    }

    public Optional<ItemTable> getItemId(String id){
        return repository.findByItemId(id);
    }
}
