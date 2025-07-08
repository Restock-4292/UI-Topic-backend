package com.restock.platform.subscription.domain.model.entities;

import com.restock.platform.subscription.domain.model.commands.RegisterPaymentCommand;
import com.restock.platform.subscription.domain.model.valueobjects.Money;
import com.restock.platform.subscription.domain.model.valueobjects.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
public class Payment {

    @Id
    private UUID id;

    private String paymentMethod;

    @Embedded
    private Money amount;

    private LocalDateTime paymentDate;

    protected Payment() {
        // For JPA
    }

    public Payment(RegisterPaymentCommand command) {
        this.id = UUID.randomUUID();
        this.paymentMethod = command.paymentMethod();
        this.amount = new Money(command.amount(), command.currency());
        this.paymentDate = LocalDateTime.now();
    }
}
