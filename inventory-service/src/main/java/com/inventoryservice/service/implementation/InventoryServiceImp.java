package com.inventoryservice.service.implementation;

import org.springframework.stereotype.Service;

import com.inventoryservice.dto.InventoryCreateDTO;
import com.inventoryservice.dto.InventoryResponseDTO;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByProductId'");
    }

    @Override
    public InventoryResponseDTO create(InventoryCreateDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public void UpdateStock(Long productId, Integer quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'UpdateStock'");
    }

    @Override
    public void releaseStock(Long productId, Integer quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'releaseStock'");
    }

}
