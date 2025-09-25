package com.inventoryservice.service.implementation;

import org.springframework.stereotype.Service;

import com.inventoryservice.dto.InventoryCreateDTO;
import com.inventoryservice.dto.InventoryResponseDTO;
import com.inventoryservice.entity.Inventory;
import com.inventoryservice.exception.ResourceNotFoundException;
import com.inventoryservice.exception.DuplicatedResourceException;
import com.inventoryservice.mapper.InventoryMapper;
import com.inventoryservice.repository.InventoryRepository;
import com.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImp implements InventoryService {
    
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    public InventoryResponseDTO findByProductId(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Stock not found for product id: " + productId));

        return inventoryMapper.toResponseDTO(inventory);
    }

    @Override
    public InventoryResponseDTO create(InventoryCreateDTO dto) {
        inventoryRepository.findByProductId(dto.getProductId()).ifPresent(existing -> {
            throw new DuplicatedResourceException("Product with id " + dto.getProductId() + " already exists.");
        });
        
        Inventory inventory = inventoryMapper.toEntity(dto);
        inventory = inventoryRepository.save(inventory);

        return inventoryMapper.toResponseDTO(inventory);
    }

    @Override
    public void UpdateStock(Long productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Stock not found for product id: " + productId));

        if (inventory.getQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for product id: " + productId);
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);
    }

    @Override
    public void releaseStock(Long productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Stock not found for product id: " + productId));

        inventory.setQuantity(inventory.getQuantity() + quantity);
    }

}
