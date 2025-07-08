package com.restock.platform.subscription.application.internal.commandservices;

import com.restock.platform.subscription.domain.model.commands.CancelSubscriptionCommand;
import com.restock.platform.subscription.domain.model.commands.RegisterSubscriptionCommand;
import com.restock.platform.subscription.domain.model.commands.SeedSubscriptionPlansCommand;
import com.restock.platform.subscription.domain.model.entities.Subscription;
import com.restock.platform.subscription.domain.model.entities.SubscriptionPlan;
import com.restock.platform.subscription.domain.model.valueobjects.Money;
import com.restock.platform.subscription.domain.services.SubscriptionCommandService;
import com.restock.platform.subscription.infrastructure.persistence.jpa.repositories.AccountRepository;
import com.restock.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionPlanRepository;
import com.restock.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {

    private final AccountRepository accountRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionCommandServiceImpl(AccountRepository accountRepository,
                                          SubscriptionPlanRepository subscriptionPlanRepository,
                                          SubscriptionRepository subscriptionRepository) {
        this.accountRepository = accountRepository;
        this.subscriptionPlanRepository = subscriptionPlanRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Optional<Subscription> handle(CancelSubscriptionCommand command) {
        var account = accountRepository.findById(command.accountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        var subscription = account.getSubscriptions().stream()
                .filter(sub -> sub.getId().equals(command.subscriptionId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));

        subscription.cancel();
        accountRepository.save(account);
        return Optional.of(subscription);
    }

    @Override
    public Optional<Subscription> handle(RegisterSubscriptionCommand command) {
        var account = accountRepository.findById(command.accountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        var plan = subscriptionPlanRepository.findById(command.subscriptionPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Subscription plan not found"));

        var subscription = new Subscription(plan);

        account.addSubscription(subscription);
        accountRepository.save(account);

        return Optional.of(subscription);
    }

    public void handle(SeedSubscriptionPlansCommand command) {
        if (subscriptionPlanRepository.count() > 0) return;

        var plans = List.of(
                new SubscriptionPlan("Monthly plan", 1, new Money(59.99, "USD"), true, false, List.of(
                        "Automated inventory management", "Ordering and purchasing control", "Reporting and analytics", "Critical stock notifications", "Integration with suppliers"
                ), 1),
                new SubscriptionPlan("Anual plan", 12, new Money(39.99, "USD"), true, true, List.of(
                        "Gestión de inventario automatizado", "Control de pedidos y compras", "Reporte y analítica", "Notificaciones de stock crítico", "Integración con proveedores"
                ), 1),
                new SubscriptionPlan("Semiannual plan", 6, new Money(49.99, "USD"), true, false, List.of(
                        "Automated inventory management", "Ordering and purchasing control", "Reporting and analytics", "Critical stock notifications", "Integration with suppliers"
                ), 1),
                new SubscriptionPlan("Monthly plan", 1, new Money(59.99, "USD"), true, false, List.of(
                        "Automated Inventory Management", "Order Control", "Reporting and Analytics", "Order Notifications", "Restaurant Rating Section"
                ), 2),
                new SubscriptionPlan("Anual plan", 12, new Money(39.99, "USD"), true, true, List.of(
                        "Automated Inventory Management", "Order Control", "Reporting and Analytics", "Order Notifications", "Restaurant Rating Section"
                ), 2),
                new SubscriptionPlan("Semiannual plan", 6, new Money(49.99, "USD"), true, false, List.of(
                        "Automated Inventory Management", "Order Control", "Reporting and Analytics", "Order Notifications", "Restaurant Rating Section"
                ), 2)
        );

        subscriptionPlanRepository.saveAll(plans);
    }

}