package com.restock.platform.resource.domain.model.aggregates;

import com.restock.platform.resource.domain.model.commands.CreateBatchCommand;
import com.restock.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Batch aggregate root.
 *
 * Represents a batch of supplies registered by a user.
 * A batch contains stock information and expiration date.
 */
@Entity
public class Batch extends AuditableAbstractAggregateRoot<Batch> {

    @Getter
    private Long userId;

    @Getter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "custom_supply_id")
    private CustomSupply customSupply;

    @Getter
    @Column(columnDefinition = "FLOAT")
    private Double stock;


    @Getter
    @Column(columnDefinition = "DATE")
    private LocalDate expirationDate;

    protected Batch() {
        // For JPA
    }

    /**
     * Constructs a Batch with all its required data.
     *
     * @param userId The ID of the user who created the batch.
     * @param customSupply The supply entity this batch is associated with.
     * @param stock The amount of stock in the batch.
     * @param expirationDate The expiration date of the batch.
     */
    public Batch(Long userId, CustomSupply customSupply, Double stock, LocalDate expirationDate) {
        this.userId = userId;
        this.customSupply = customSupply;
        this.stock = stock;
        this.expirationDate = expirationDate;
    }

    /**
     * Constructs a Batch from a CreateBatchCommand.
     *
     * @param command The command containing batch creation data.
     * @param customSupply The supply associated with this batch.
     */
    public Batch(CreateBatchCommand command, CustomSupply customSupply) {
        this(command.userId(), customSupply, command.stock(), command.expirationDate());
    }
    public Batch update(Double newStock, LocalDate newExpirationDate) {
        if (newStock != null) this.stock = newStock;
        if (newExpirationDate != null) this.expirationDate = newExpirationDate;
        return this;
    }
}
