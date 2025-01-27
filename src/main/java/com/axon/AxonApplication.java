package com.axon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.axon")
@RestController
public class AxonApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxonApplication.class, args);
    }
}
