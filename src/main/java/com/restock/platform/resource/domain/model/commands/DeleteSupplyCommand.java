package com.restock.platform.resource.domain.model.commands;

public record DeleteSupplyCommand(Long supplyId) {
    public DeleteSupplyCommand {
        if (supplyId == null || supplyId <= 0) {
            throw new IllegalArgumentException("Supply ID must be a positive number");
        }
    }
}
