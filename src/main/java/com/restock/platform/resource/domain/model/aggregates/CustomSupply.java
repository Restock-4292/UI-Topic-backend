package com.restock.platform.resource.domain.model.aggregates;

import com.restock.platform.resource.domain.model.commands.CreateCustomSupplyCommand;
import com.restock.platform.resource.domain.model.valueobjects.StockRange;
import com.restock.platform.resource.domain.model.valueobjects.Price;
import com.restock.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class CustomSupply extends AuditableAbstractAggregateRoot<CustomSupply> {

    @Getter
    private Long userId;

    @Getter
    private Long supplyId;

    @Getter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "min", column = @Column(name = "stock_min", columnDefinition = "FLOAT")),
            @AttributeOverride(name = "max", column = @Column(name = "stock_max", columnDefinition = "FLOAT"))
    })
    private StockRange stockRange;

    @Getter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price_amount", columnDefinition = "FLOAT")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency", length = 3))
    })
    private Price price;

    @Getter
    private String description;

    protected CustomSupply() {
        // Para JPA
    }

    public CustomSupply(CreateCustomSupplyCommand command) {
        this.supplyId = command.supplyId();
        this.stockRange = command.stockRange();
        this.price = command.price();
        this.description = command.description();
        this.userId = command.userId();
    }

    public CustomSupply update(StockRange stockRange, Price price, String description) {
        this.stockRange = stockRange;
        this.price = price;
        this.description = description;
        return this;
    }
}