package com.application.orders.service.impl;

import com.application.orders.dto.OrderDTO;
import com.application.orders.model.Order;
import com.application.orders.model.OrderDetail;
import com.application.orders.repository.OrderDetailRepository;
import com.application.orders.repository.OrderRepository;
import com.application.orders.service.OrderService;
import com.application.orders.utils.OrderMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderMapper  orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }



    @Override
    @Transactional
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderDTO dto = orderMapper.toDTO(order);
        dto.setOrderDetails(order.getOrderDetails()
                .stream()
                .map(orderMapper::toDetailDTO)
                .collect(Collectors.toList()));

        return order != null ? orderMapper.toDTO(order) : null;
    }

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        logger.info("Create Order");
        Order order = orderMapper.toEntity(orderDTO);

        if (order.getOrderDetails() != null) {
            for (OrderDetail detail : order.getOrderDetails()) {
                detail.setOrder(order);
            }
        }

        order = orderRepository.save(order);

        return orderMapper.toDTO(order);
    }
}
