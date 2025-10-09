package com.inventoryservice.service;

import com.inventoryservice.dto.InventoryCreateDTO;
import com.inventoryservice.dto.InventoryResponseDTO;

public interface InventoryService {

    InventoryResponseDTO findByProductId(Long productId);

    InventoryResponseDTO create(InventoryCreateDTO dto);

    void UpdateStock(Long productId, Integer quantity);

    void releaseStock(Long productId, Integer quantity);
    
    boolean hasSufficientStock(Long productId, int quantity);
}
