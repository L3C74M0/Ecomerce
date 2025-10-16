package com.orderservice.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.orderservice.dto.OrderCreateDTO;
import com.orderservice.dto.OrderViewDTO;
import com.orderservice.entity.Order;
import com.orderservice.entity.OrderStatus;
import com.orderservice.exception.OrderAlreadyConfirmedException;
import com.orderservice.exception.ResourceNotFoundException;
import com.orderservice.mapper.OrderMapper;
import com.orderservice.messaging.OrderEventProducer;
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
    private final OrderEventProducer orderEventProducer;

    @Override
    public OrderViewDTO createOrder(OrderCreateDTO dto) {
        Order order = orderMapper.toEntity(dto);
        order.calculateTotalAmount();
        order.setStatus(OrderStatus.PENDING);

        Order saveOrder = orderRepository.save(order);

        orderEventProducer.sendOrderCreatedEvent(saveOrder);

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
            throw new OrderAlreadyConfirmedException("Cannot cancel a confirmed order");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

}
