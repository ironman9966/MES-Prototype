package com.example.mes.productionorder;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    DepartmentService(DepartmentRepository repository){
        this.repository = repository;
    }

    public List<Department> findAllDepartments(){
        return repository.findAll();
    }

    public Optional<Department> findByName(String name){
        return repository.findByName(name);
    }

    public Department createDepartment(DepartmentRequest request){
        Department department = new Department();
        department.setName(request.name());
        department.setCapacity(request.capacity());

        return repository.save(department);
    }
}
