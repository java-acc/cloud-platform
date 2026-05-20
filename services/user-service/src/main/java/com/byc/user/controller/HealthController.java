package com.byc.user.controller;

import com.byc.common.core.response.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class HealthController {
    
    @Value("${spring.application.name}")
    private String app;
    
    @GetMapping("/ping")
    public R<Map<String, Object>> ping() {
        return R.ok(Map.of("app", app, "status", "UP", "module", "user-service"));
    }
}
