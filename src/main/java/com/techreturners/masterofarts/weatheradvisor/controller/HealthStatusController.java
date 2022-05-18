package com.techreturners.masterofarts.weatheradvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthStatusController {

    @Operation(summary = "Extended basic endpoint if the service is up")
    @GetMapping({"/", "/health"})
    public ResponseEntity<Map<String, Object>> checkHealth(@RequestHeader Map<String, String> headers) {
        String host = headers.getOrDefault("host", "a host");

        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");

        // no longer want to expose these: health.put("headers", headers);

        Map<String, Object> info = new HashMap<>();
        info.put("health", "http://" + host + "/health");
        info.put("actuator", "http://" + host + "/actuator");
        info.put("endpoints", "http://" + host + "/swagger-ui/index.html");
        health.put("info", info);
        return new ResponseEntity<>(health, HttpStatus.OK);
    }
}
