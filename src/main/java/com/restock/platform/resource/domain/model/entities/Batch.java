package com.restock.platform.resource.domain.model.entities;
import java.time.LocalDate;

public class Batch {
    private Integer id;             // Integer en lugar de int para soportar null
    private int supplyId;
    private int stock;
    private LocalDate expirationDate;  // LocalDate soporta null por defecto
    private int userId;

    // Constructor
    public Batch(Integer id, int supplyId, int stock, LocalDate expirationDate, int userId) {
        this.id = id;
        this.supplyId = supplyId;
        this.stock = stock;
        this.expirationDate = expirationDate;
        this.userId = userId;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public int getSupplyId() {
        return supplyId;
    }

    public int getStock() {
        return stock;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public int getUserId() {
        return userId;
    }
}