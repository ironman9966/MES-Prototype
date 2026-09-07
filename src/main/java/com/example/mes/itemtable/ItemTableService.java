package com.example.mes.itemtable;

import java.util.List;
import java.util.Optional;

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

    public ItemTable createItem(ItemTable item){
        return repository.save(item);
    }
}
