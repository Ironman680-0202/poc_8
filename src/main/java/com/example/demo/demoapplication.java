package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class demoapplication {

    @GetMapping("/")
    public String home() {
        return "Hello from Java + Docker + Jenkins + SonarQube 🚀";
    }

    public static void main(String[] args) {
        SpringApplication.run(demoapplication.class, args);
    }
}