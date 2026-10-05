package com.example.mes.productionorder;

import org.apache.catalina.connector.Response;
import org.aspectj.weaver.patterns.DeclareParentsMixin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService service;

    public DepartmentController(DepartmentService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentRequest request){
        Department saved = service.createDepartment(request);
        URI location = URI.create("department:" + saved.getName());
        return ResponseEntity.created(location).body(saved);
    }

}
