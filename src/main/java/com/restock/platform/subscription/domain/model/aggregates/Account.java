package com.restock.platform.subscription.domain.model.aggregates;

import com.restock.platform.subscription.domain.model.commands.CreateAccountCommand;
import com.restock.platform.subscription.domain.model.entities.Payment;
import com.restock.platform.subscription.domain.model.entities.Subscription;
import com.restock.platform.subscription.domain.model.entities.SubscriptionPlan;
import com.restock.platform.subscription.domain.model.valueobjects.Money;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
public class Account {

    @Id
    private UUID id;

    private UUID userId;

    private String email;

    @Column(name = "subscription_plan_type")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscription> subscriptions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();

    protected Account() {
        // For JPA
    }

    public Account(CreateAccountCommand command, SubscriptionPlan plan) {
        this.id = UUID.randomUUID();
        this.userId = command.userId();
        this.email = command.email();
        this.subscriptions = new ArrayList<>();
        this.payments = new ArrayList<>();

        var subscription = new Subscription(plan);
        this.subscriptions.add(subscription);
    }

    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    public void registerPayment(Payment payment) {
        payments.add(payment);
    }
}
