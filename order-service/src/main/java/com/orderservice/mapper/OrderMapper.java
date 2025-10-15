package com.orderservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.orderservice.dto.OrderCreateDTO;
import com.orderservice.dto.OrderItemDTO;
import com.orderservice.dto.OrderViewDTO;
import com.orderservice.entity.Order;
import com.orderservice.entity.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderItem toOrderItem(OrderItemDTO dto);
    OrderItemDTO tOrderItemDTO(OrderItem entity);
    List<OrderItem> toOtderItemList(List<OrderItemDTO> dtoList);
    List<OrderItemDTO> tOrderItemDTOList(List<OrderItem> entityList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "totalAmount", ignore = true) //Amount is calculated later
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "items", target = "items")
    Order toEntity(OrderCreateDTO dto);

    @Mapping(source = "items", target = "items")
    @Mapping(source = "status", target = "status")
    OrderViewDTO toviewDTO(Order order);
}
