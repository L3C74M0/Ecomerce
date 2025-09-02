package com.authservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "✅ Endpoint público accesible sin autenticación.";
    }

    @GetMapping("/secure")
    public String secureEndpoint() {
        return "🔐 Endpoint protegido, necesitas JWT válido.";
    }
}