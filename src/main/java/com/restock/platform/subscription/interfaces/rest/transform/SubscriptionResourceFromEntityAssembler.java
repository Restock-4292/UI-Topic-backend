package com.restock.platform.subscription.interfaces.rest.transform;

import com.restock.platform.subscription.domain.model.entities.Subscription;
import com.restock.platform.subscription.domain.model.valueobjects.Money;
import com.restock.platform.subscription.interfaces.rest.resources.SubscriptionResource;

import java.util.List;
import java.util.UUID;

public class SubscriptionResourceFromEntityAssembler {
    public static SubscriptionResource toResourceFromEntity(Subscription entity) {
        UUID accountIdFromContext = null;

        UUID subscriptionPlanId = (entity.getPlan() != null) ? entity.getPlan().getId() : null;
        String planType = (entity.getPlan() != null && entity.getPlan().getPlanType() != null) ? entity.getPlan().getPlanType().name() : null;
        int durationInMonths = (entity.getPlan() != null) ? entity.getPlan().getDurationInMonths() : 0;

        double price = 0.0;
        String currency = null;
        if (entity.getPlan() != null && entity.getPlan().getPrice() != null) {
            Money planPrice = entity.getPlan().getPrice();
            price = planPrice.amount();
            currency = planPrice.currency();
        }

        boolean popular = (entity.getPlan() != null) ? entity.getPlan().isPopular() : false;
        List<String> features = (entity.getPlan() != null) ? entity.getPlan().getFeatures() : null;


        return new SubscriptionResource(
                entity.getId(),
                accountIdFromContext,
                subscriptionPlanId,
                planType,
                durationInMonths,
                price,
                currency,
                popular,
                features,
                entity.getStartDate().toLocalDate(),
                entity.getEndDate().toLocalDate(),
                entity.getStatus().name()
        );
    }
}