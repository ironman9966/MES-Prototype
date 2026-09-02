package com.example.mes.productionorder;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "production_operation")
public class ProductionOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer sequenceNumber;

    private String process;
    private String machine;
    private String employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "production_order_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ProductionOrder productionOrder;

    @Version
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductionOperation that)) return false;

        return Objects.equals(sequenceNumber, that.sequenceNumber)
            && Objects.equals(productionOrder, that.productionOrder);
    }

    // deliberately NOT including productionOrder in hashCode —
    // avoids the recursive-hashCode trap if ProductionOrder's
    // hashCode ever touches its operations collection
    @Override
    public int hashCode() {
        return Objects.hash(sequenceNumber);
    }
}
