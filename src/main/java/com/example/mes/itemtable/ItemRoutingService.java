package com.example.mes.itemtable;

import com.example.mes.exceptions.ItemNotFoundException;
import com.example.mes.productionorder.Department;
import com.example.mes.productionorder.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemRoutingService {

    private final ItemRoutingRepository repository;
    private final ItemTableRepository itemTableRepository;
    private final DepartmentRepository departmentRepository;

    ItemRoutingService(ItemRoutingRepository repository, ItemTableRepository itemTableRepository, DepartmentRepository departmentRepository){
        this.repository = repository;
        this.itemTableRepository = itemTableRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<ItemRouting> getAllItemRouting(){
        return repository.findAll();
    }

    @Transactional
    public ItemRouting createItemRouting(ItemRoutingRequest itemRoutingRequest){
        ItemTable itemTable = itemTableRepository.findByItemId(itemRoutingRequest.getItemId())
                .orElseThrow(() -> new ItemNotFoundException(itemRoutingRequest.getItemId()));
        Department department = departmentRepository.findByName(itemRoutingRequest.getDepartment()).orElseThrow();

        ItemRouting itemRouting = new ItemRouting();
        itemRouting.setDepartment(department);
        itemRouting.setItemTable(itemTable);

        return repository.save(itemRouting);
    }
}
