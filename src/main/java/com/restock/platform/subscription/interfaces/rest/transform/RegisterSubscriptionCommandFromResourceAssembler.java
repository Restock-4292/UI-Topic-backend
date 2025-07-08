package com.restock.platform.subscription.interfaces.rest.transform;

import com.restock.platform.subscription.domain.model.commands.RegisterSubscriptionCommand;
import com.restock.platform.subscription.domain.model.valueobjects.SubscriptionPlanType;
import com.restock.platform.subscription.interfaces.rest.resources.RegisterSubscriptionResource;

import java.util.UUID;


public class RegisterSubscriptionCommandFromResourceAssembler {

    public static RegisterSubscriptionCommand toCommand(RegisterSubscriptionResource resource) {
        return new RegisterSubscriptionCommand(
                resource.accountId(),  // accountId
                resource.subscriptionPlanId(),  // subscriptionPlanId
                SubscriptionPlanType.valueOf(resource.plan().toString()),  // Assuming it's an enum, ensure proper mapping
                resource.durationInMonths(),  // durationInMonths
                resource.price(),  // price
                resource.currency(),  // currency
                resource.popular(),  // popular
                resource.features()  // features
        );
    }
}
