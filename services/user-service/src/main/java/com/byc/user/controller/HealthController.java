package com.byc.user.controller;

import com.byc.common.core.response.R;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health check and status controller for the user service.
 *
 * <p>Provides endpoints for service health monitoring and status verification.
 */
@RestController
@RequestMapping("/api/user")
public class HealthController {

  @Value("${spring.application.name}")
  private String app;

  /**
   * Returns the health status of the user service.
   *
   * @return a response containing application name, status, and module information
   */
  @GetMapping("/ping")
  public R<Map<String, Object>> ping() {
    return R.ok(Map.of("app", app, "status", "UP", "module", "user-service"));
  }
}
