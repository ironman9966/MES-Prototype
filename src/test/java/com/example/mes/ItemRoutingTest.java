package com.example.mes;

import com.example.mes.itemtable.*;
import com.example.mes.productionorder.Department;
import com.example.mes.productionorder.DepartmentRepository;
import com.example.mes.salesorder.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ItemRoutingTest {

    @Mock
    private ItemRoutingRepository repository;

    @Mock
    private ItemTableRepository itemTableRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private ItemRoutingService service;

    @Test
    void createItemRouting_ReturnOrder_WhenOrderExists(){
        ItemRoutingRequest request = new ItemRoutingRequest();
        request.setDepartment("abc");
        request.setItemId("1000");

        ItemTable mockItem = new ItemTable();
        mockItem.setItemId("1000");

        Department mockDepartment = new Department();
        mockDepartment.setName("abc");

        Mockito.when(itemTableRepository.findByItemId("1000")).thenReturn(Optional.of(mockItem));

        Mockito.when(departmentRepository.findByName("abc")).thenReturn(Optional.of(mockDepartment));

        //Mockito.when(repository.save(Mockito.any(ItemRouting.class)))
          //      .thenAnswer(invocation -> invocation.getArgument(0));

        ItemRouting itemRouting = service.createItemRouting(request);

        ArgumentCaptor<ItemRouting> captor =
                ArgumentCaptor.forClass(ItemRouting.class);

        Mockito.verify(repository).save(captor.capture());

        ItemRouting route = captor.getValue();

        Mockito.verify(repository).save(captor.capture());
        Assertions.assertThat(route.getDepartment().getName()).isEqualTo(request.getDepartment());
        Assertions.assertThat((route.getItemTable().getItemId())).isEqualTo(request.getItemId());

    }
}
