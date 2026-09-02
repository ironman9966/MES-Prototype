package com.example.mes.productionorder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.mes.itemtable.ItemTable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Table(name = "production_order")
public class ProductionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String orderNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_table_id", nullable = false)
    private ItemTable ItemTable;

    @Column(nullable = false)
    private int orderQty;

    @Column(nullable = false)
    private int rejectQty;

    // TODO: bomVersion is a plain string for now (prototype stage).
    // Once Product/Routing tables exist, consider replacing this with
    // a FK to a specific Routing/Product revision instead of a free-text code.
    @Column(name = "bom_version")
    private String bomVersion;   // was "serial" in the old class

    @Column(nullable = false)
    private LocalDateTime desireDate;

    private LocalDate etd;

    private int priority;

    private LocalDateTime inputDate;
    private LocalDateTime reffDate;


    // TODO: No DB-level CHECK constraint on status (dropped during prototyping —
    // see production_order_status_check). Once OrderStatus values stabilize,
    // consider re-adding a CHECK constraint via a proper Flyway migration
    // rather than a manual ALTER, so it's versioned and reviewable.
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private ProductionOrderStatus status;

    //concurrency -> optimistic concurrency control
    @Version
    private Long version;

    @OneToMany(mappedBy = "productionOrder", cascade = CascadeType.ALL,
           orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final List<ProductionOperation> operations = new ArrayList<>();

    public ProductionOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ItemTable getItemTable() {
        return ItemTable;
    }

    public void setItemTable(ItemTable itemTable) {
        this.ItemTable = itemTable;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public int getRejectQty() {
        return rejectQty;
    }

    public void setRejectQty(int rejectQty) {
        this.rejectQty = rejectQty;
    }

    public String getBomVersion() {
        return bomVersion;
    }

    public void setBomVersion(String bomVersion) {
        this.bomVersion = bomVersion;
    }

    public LocalDateTime getDesireDate() {
        return desireDate;
    }

    public void setDesireDate(LocalDateTime desireDate) {
        this.desireDate = desireDate;
    }

    public LocalDate getEtd() {
        return etd;
    }

    public void setEtd(LocalDate etd) {
        this.etd = etd;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDateTime getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDateTime inputDate) {
        this.inputDate = inputDate;
    }

    public ProductionOrderStatus getStatus() {
        return status;
    }

    public void setStatus(ProductionOrderStatus status) {
        this.status = status;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public LocalDateTime getReffDate() {
        return reffDate;
    }

    public void setReffDate(LocalDateTime reffDate) {
        this.reffDate = reffDate;
    }

    public List<ProductionOperation> getProductionOperation(){
        return operations;
    }

    public void setProductionOperation(ProductionOperation po){
        this.operations.add(po);
    }
    // Helper methods to keep both sides of the bidirectional link in sync —
    // this is the part people forget and then get subtle bugs where
    // operation.getProductionOrder()    is null even though it's in the list
    public void addOperation(ProductionOperation op) {
        operations.add(op);
        op.setProductionOrder(this);
    }

    public void removeOperation(ProductionOperation op) {
        operations.remove(op);
        op.setProductionOrder(null);
    }
}