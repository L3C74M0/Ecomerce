package com.orderservice.exception;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.orderservice.dto.OrderCreateDTO;
import com.orderservice.dto.OrderViewDTO;
import com.orderservice.entity.Order;
import com.orderservice.entity.OrderItem;
import com.orderservice.entity.OrderStatus;
import com.orderservice.mapper.OrderMapper;
import com.orderservice.repository.OrderRepository;
import com.orderservice.service.OrderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderViewDTO createOrder(OrderCreateDTO dto) {
        Order order = orderMapper.toEntity(dto);
        
        BigDecimal totalAmount = order.getItems().stream()
            .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(totalAmount);

        for (OrderItem item : order.getItems()) {
            item.setOrder(order);
        }

        Order saveOrder = orderRepository.save(order);

        return orderMapper.toviewDTO(saveOrder);
    }

    @Override
    public OrderViewDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));

        return orderMapper.toviewDTO(order);
    }

    @Override
    public List<OrderViewDTO> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status.toUpperCase()).stream()
            .map(orderMapper::toviewDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<OrderViewDTO> getAllOrders() {
        return orderRepository.findAll().stream()
        .map(orderMapper::toviewDTO)
        .collect(Collectors.toList());
    }

    @Override
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));

        if (order.getStatus().equals(OrderStatus.CONFIRMED)) {
            throw new IllegalStateException("Cannot cancel a confirmed order");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

}
