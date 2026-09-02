package com.example.mes.salesorder;

import java.util.*;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity()
@Getter
@Setter
@Table(name="sales_order")
public class SalesOrder {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false ,unique=true, length=20)
    private String salesOrderNumber;

    @OneToMany(mappedBy="salesOrder",cascade=CascadeType.ALL , orphanRemoval=true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<SalesLine> salesLine = new ArrayList<>();

    @Enumerated
    private OrderStatus orderStatus;

    public enum OrderStatus{
        Open,
        Picked,
        Invoiced,
        Cancelled
    }

    public void addSalesLine(SalesLine line){
        line.setSalesOrder(this);
        salesLine.add(line);
    }
}
