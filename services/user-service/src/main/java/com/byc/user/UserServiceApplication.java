package com.byc.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Spring Boot application entry point for the user service.
 *
 * <p>Provides user management functionality including registration, authentication,
 * and profile management across the cloud platform.
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class UserServiceApplication {

  /**
   * Main method to start the user service application.
   *
   * @param args command-line arguments passed to the application
   */
  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }
}
