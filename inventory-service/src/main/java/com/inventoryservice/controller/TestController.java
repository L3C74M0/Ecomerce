package com.inventoryservice.controller;

import org.springframework.web.bind.annotation.*;
import com.inventoryservice.dto.InventoryCreateDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class TestController {

    @PostMapping
    public String recibirJson(@Valid @RequestBody InventoryCreateDTO dto) {
        System.out.println(dto.getProductId());
        System.out.println(dto.getQuantity());

        return dto.toString();
    }

}
