package com.byc.sync.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot application entry point for the scheduled synchronization job service.
 *
 * <p>Provides scheduled task execution for data synchronization across
 * microservices in the cloud platform.
 */
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class SyncJobApplication {

  /**
   * Main method to start the sync job application.
   *
   * @param args command-line arguments passed to the application
   */
  public static void main(String[] args) {
    SpringApplication.run(SyncJobApplication.class, args);
  }
}
