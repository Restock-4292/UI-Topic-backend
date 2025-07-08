package com.restock.platform.subscription.interfaces.rest.transform;

import com.restock.platform.subscription.domain.model.commands.CreateAccountCommand;
import com.restock.platform.subscription.interfaces.rest.resources.RegisterAccountResource;

import java.util.UUID;

public class RegisterAccountCommandFromResourceAssembler {
    public static CreateAccountCommand toCommandFromResource(RegisterAccountResource resource) {
        return new CreateAccountCommand(
                UUID.randomUUID(),
                resource.email(),
                UUID.fromString(resource.subscriptionPlanId())
        );
    }
}


