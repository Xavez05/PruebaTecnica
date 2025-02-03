package com.application.payment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private Long orderId;
    private Double amount;
    private String paymentStatus;
    private LocalDateTime paymentDate;
}
