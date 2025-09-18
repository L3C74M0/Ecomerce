package com.inventoryservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryservice.dto.InventoryCreateDTO;
import com.inventoryservice.dto.InventoryResponseDTO;
import com.inventoryservice.service.InventoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponseDTO> getProductById(@PathVariable Long productId) {
        InventoryResponseDTO response = inventoryService.findByProductId(productId);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<InventoryResponseDTO> create(@Valid @RequestBody InventoryCreateDTO dto) {
        InventoryResponseDTO response = inventoryService.create(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{productId}/decrement")
    public ResponseEntity<Void> decrementStock(@PathVariable Long productId, @RequestParam("quantity") Integer quantity) {
        inventoryService.UpdateStock(productId, quantity);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{productId}/release")
    public ResponseEntity<Void> releaseStock(@PathVariable Long productId, @RequestParam("quantity") Integer quantity) {
        inventoryService.releaseStock(productId, quantity);

        return ResponseEntity.noContent().build();
    }

}
