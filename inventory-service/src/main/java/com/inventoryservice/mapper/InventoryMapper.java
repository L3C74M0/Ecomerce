package com.inventoryservice.mapper;

import com.inventoryservice.dto.InventoryCreateDTO;
import com.inventoryservice.dto.InventoryResponseDTO;
import com.inventoryservice.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "productId", ignore = true)
    Inventory toEntity(InventoryCreateDTO dto);

    InventoryResponseDTO toResponseDTO(Inventory inventory);

}
