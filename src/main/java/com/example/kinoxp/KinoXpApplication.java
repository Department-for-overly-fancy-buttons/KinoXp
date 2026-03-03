package com.example.kinoxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KinoXpApplication {
    static void main(String[] args) {
        System.out.println("Hello from srping application before boot");
        SpringApplication.run(KinoXpApplication.class, args);
        System.out.println("Hello from srping application after boot");

    }
}
