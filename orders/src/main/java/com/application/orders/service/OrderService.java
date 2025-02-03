package com.application.orders.service;

import com.application.orders.dto.OrderDTO;
import com.application.orders.model.OrderDetail;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Long id);
    OrderDTO createOrder(OrderDTO orderDTO);
}
