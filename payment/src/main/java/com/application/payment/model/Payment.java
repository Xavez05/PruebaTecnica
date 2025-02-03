package com.application.payment.model;

import lombok.Data;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Double amount;
    private String paymentStatus;
    private LocalDateTime paymentDate;


    @PrePersist
    protected void onCreate() {
        this.paymentDate = LocalDateTime.now();
    }
}