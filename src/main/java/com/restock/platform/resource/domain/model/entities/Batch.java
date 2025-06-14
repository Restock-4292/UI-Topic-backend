package com.restock.platform.resource.domain.model.entities;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

public class Batch {

    @Id
    @Getter
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(nullable = false, updatable = false)
    private Long supplyId;

    @Getter
    private int stock;

    @Getter
    @CreatedDate
    private LocalDate expirationDate;

    @Getter
    @Column(nullable = false, updatable = false)
    private Long userId;

    // Constructor
    public Batch(Long id, Long supplyId, int stock, LocalDate expirationDate, Long userId) {
        this.id = id;
        this.supplyId = supplyId;
        this.stock = stock;
        this.expirationDate = expirationDate;
        this.userId = userId;
    }
}