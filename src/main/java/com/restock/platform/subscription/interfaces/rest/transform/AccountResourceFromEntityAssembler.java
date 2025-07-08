package com.restock.platform.subscription.interfaces.rest.transform;

import com.restock.platform.subscription.domain.model.aggregates.Account;
import com.restock.platform.subscription.interfaces.rest.resources.AccountResource;

import java.util.UUID;

public class AccountResourceFromEntityAssembler {

    public static AccountResource toResourceFromEntity(Account entity) {
        UUID firstSubscriptionPlanId = null;
        if (entity.getSubscriptions() != null && !entity.getSubscriptions().isEmpty()) {
            firstSubscriptionPlanId = entity.getSubscriptions().get(0).getPlan().getId();
        }

        return new AccountResource(
                entity.getId(),
                entity.getEmail(),
                firstSubscriptionPlanId
        );
    }
}
