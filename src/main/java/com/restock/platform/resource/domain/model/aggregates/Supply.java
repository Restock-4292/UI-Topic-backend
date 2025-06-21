package com.restock.platform.resource.domain.model.aggregates;

import com.restock.platform.resource.domain.model.valueobjects.StockRange;
import com.restock.platform.resource.domain.model.valueobjects.Price;
import com.restock.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Supply extends AuditableAbstractAggregateRoot<Supply> {

    @Getter
    private Long referenceSupplyId;

    @Getter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "min", column = @Column(name = "stock_min")),
            @AttributeOverride(name = "max", column = @Column(name = "stock_max"))
    })
    private StockRange stockRange;

    @Getter
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price_amount"))
    private Price price;

    @Getter
    private String description;

    @Getter
    private Long userId;

    protected Supply() {
        // Para JPA
    }

    public Supply(Long referenceSupplyId, StockRange stockRange, Price price, String description, Long userId) {
        this.referenceSupplyId = referenceSupplyId;
        this.stockRange = stockRange;
        this.price = price;
        this.description = description;
        this.userId = userId;
    }

    public Supply update(StockRange stockRange, Price price, String description) {
        this.stockRange = stockRange;
        this.price = price;
        this.description = description;
        return this;
    }
}