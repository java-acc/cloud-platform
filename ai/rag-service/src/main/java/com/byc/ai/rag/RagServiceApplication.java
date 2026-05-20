package com.byc.ai.rag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RagServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RagServiceApplication.class, args);
    }
}
