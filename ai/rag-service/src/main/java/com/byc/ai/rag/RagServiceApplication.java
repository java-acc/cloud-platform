package com.byc.ai.rag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Spring Boot application entry point for the RAG (Retrieval-Augmented Generation) service.
 *
 * <p>This service provides AI-powered question answering using vector database retrieval
 * and large language models.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RagServiceApplication {

  /**
   * Main method to start the RAG service application.
   *
   * @param args command-line arguments passed to the application
   */
  public static void main(String[] args) {
    SpringApplication.run(RagServiceApplication.class, args);
  }
}
