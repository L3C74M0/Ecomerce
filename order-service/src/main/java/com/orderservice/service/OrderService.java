package com.orderservice.service;

import java.util.List;

import com.orderservice.dto.OrderCreateDTO;
import com.orderservice.dto.OrderViewDTO;

public interface OrderService {

    OrderViewDTO createOrder(OrderCreateDTO dto);

    OrderViewDTO getOrderById(Long id);

    List<OrderViewDTO> getOrdersByStatus(String status);

    List<OrderViewDTO> getAllOrders();

    void cancelOrder(Long id);

}
