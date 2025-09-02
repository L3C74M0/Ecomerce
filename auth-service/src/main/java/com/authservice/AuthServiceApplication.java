package com.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        // 👇 Cargar el archivo .env antes de iniciar Spring Boot
        Dotenv dotenv = Dotenv.configure()
            .directory("./") // o "./auth-service" si estás fuera del microservicio
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

        // Cargar las variables de entorno manualmente
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));

        SpringApplication.run(AuthServiceApplication.class, args);
    }
}