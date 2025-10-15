package com.orderservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderservice.dto.OrderCreateDTO;
import com.orderservice.dto.OrderViewDTO;
import com.orderservice.service.OrderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping    
    public ResponseEntity<OrderViewDTO> createOrder(@Valid @RequestBody OrderCreateDTO dto) {
        OrderViewDTO createOrder = orderService.createOrder(dto);
        return new ResponseEntity<>(createOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderViewDTO> getOrderById(@PathVariable Long id) {
        OrderViewDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
    
    @GetMapping(params = "status")
    public ResponseEntity<List<OrderViewDTO>> getOrderByStatus(@RequestParam String status) {
        List<OrderViewDTO> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping
    public ResponseEntity<List<OrderViewDTO>> getAllOrders() {
        List<OrderViewDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }   

}
