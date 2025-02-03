package com.application.orders.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private Long customerId;
    private Double totalAmount;
    private List<OrderDetailDTO> orderDetails;
}
