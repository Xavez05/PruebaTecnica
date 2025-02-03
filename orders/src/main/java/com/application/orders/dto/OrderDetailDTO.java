package com.application.orders.dto;

import lombok.Data;

@Data
public class OrderDetailDTO {
    private Long productId;
    private Integer quantity;
    private Double price;
}
