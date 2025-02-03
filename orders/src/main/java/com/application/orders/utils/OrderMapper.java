package com.application.orders.utils;

import com.application.orders.dto.OrderDTO;
import com.application.orders.dto.OrderDetailDTO;
import com.application.orders.model.Order;
import com.application.orders.model.OrderDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper  {

    public OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomerId());
        dto.setTotalAmount(order.getTotalAmount());
        List<OrderDetailDTO> details = order.getOrderDetails().stream()
                .map(this::toDetailDTO)
                .collect(Collectors.toList());
        dto.setOrderDetails(details);
        return dto;
    }

    public Order toEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCustomerId(orderDTO.getCustomerId());
        order.setTotalAmount(orderDTO.getTotalAmount());

        List<OrderDetail> details = orderDTO.getOrderDetails().stream()
                .map(this::toDetailEntity)
                .collect(Collectors.toList());
        order.setOrderDetails(details);
        return order;
    }

    public OrderDetailDTO toDetailDTO(OrderDetail detail) {
        OrderDetailDTO detailDTO = new OrderDetailDTO();
        detailDTO.setProductId(detail.getProductId());
        detailDTO.setQuantity(detail.getQuantity());
        detailDTO.setPrice(detail.getPrice());
        return detailDTO;
    }

    public OrderDetail toDetailEntity(OrderDetailDTO detailDTO) {
        OrderDetail detail = new OrderDetail();
        detail.setProductId(detailDTO.getProductId());
        detail.setQuantity(detailDTO.getQuantity());
        detail.setPrice(detailDTO.getPrice());
        return detail;
    }

}
