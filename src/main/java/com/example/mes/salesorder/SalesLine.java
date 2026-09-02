package com.example.mes.salesorder;

import com.example.mes.itemtable.ItemTable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name="sales_line")
public class SalesLine {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sales_order_id", nullable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SalesOrder salesOrder;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="item_table_id", nullable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ItemTable itemId;

    private Long quantity;
}
