package com.payment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    @Column(nullable = false, updatable = false)
    private String createdBy;

    @Column(nullable = false, updatable = false)
    private String updatedBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted = false;

    @PrePersist
    protected void onCreate() {
        uuid = UUID.randomUUID().toString();
        createdAt = new Timestamp(System.currentTimeMillis()).toLocalDateTime();
        createdBy = uuid;
        updatedAt = new Timestamp(System.currentTimeMillis()).toLocalDateTime();
        updatedBy = uuid;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedBy = uuid;
        updatedAt = new Timestamp(System.currentTimeMillis()).toLocalDateTime();;
    }
}

