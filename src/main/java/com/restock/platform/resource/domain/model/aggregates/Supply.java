package com.restock.platform.resource.domain.model.aggregates;

import com.restock.platform.resource.domain.model.entities.Category;
import com.restock.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.restock.platform.resource.domain.model.valueobjects.UnitMeasurement;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Supply extends AuditableAbstractAggregateRoot<Supply> {

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private boolean perishable;

    @Getter
    private int minStock;

    @Getter
    private int maxStock;

    @Getter
    private double price;

    @Getter
    private Long userId;

    @Getter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "unit_name")),
            @AttributeOverride(name = "abbreviation", column = @Column(name = "unit_abbreviation"))
    })
    private UnitMeasurement unitMeasurement;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    protected Supply() {
        // For JPA
    }

    public Supply(String name, String description, boolean perishable, int minStock, int maxStock,
                  double price, Long userId, UnitMeasurement unitMeasurement, Category category) {
        this.name = name;
        this.description = description;
        this.perishable = perishable;
        this.minStock = minStock;
        this.maxStock = maxStock;
        this.price = price;
        this.userId = userId;
        this.unitMeasurement = unitMeasurement;
        this.category = category;
    }

    public Supply update(String name, String description, boolean perishable, int minStock, int maxStock,
                         double price, UnitMeasurement unitMeasurement, Category category) {
        this.name = name;
        this.description = description;
        this.perishable = perishable;
        this.minStock = minStock;
        this.maxStock = maxStock;
        this.price = price;
        this.unitMeasurement = unitMeasurement;
        this.category = category;
        return this;
    }
}
