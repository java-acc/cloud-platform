package com.byc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Spring Boot application entry point for the API gateway service.
 *
 * <p>Provides routing, filtering, and load balancing for microservice requests
 * across the cloud platform.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayServiceApplication {

  /**
   * Main method to start the gateway service application.
   *
   * @param args command-line arguments passed to the application
   */
  public static void main(String[] args) {
    SpringApplication.run(GatewayServiceApplication.class, args);
  }
}
