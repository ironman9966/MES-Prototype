package com.example.mes.itemtable;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "item_table",
	    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"itemId", "revision"}
        )
    }
)
@Getter
@Setter
public class ItemTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String itemId;

    private String description;
	private String drawingNo;
	private String drawingRev;
	private String itemNo;
	private String material;
    private String treeBar;
	private boolean useWaterWax;
	private boolean usePatternWax;
	private Integer weightWaterWax;
	private Integer pcsPerTree;
	private Integer pcsperTray;
	private Integer trayperCart;
	private Integer cavity;
	private Integer revision;
	private Double scrapRate;

    @OneToMany(mappedBy = "itemTable", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<ItemRouting> itemRouting = new ArrayList<>();

	@Version
    private Long version;
}
