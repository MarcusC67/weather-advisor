package com.techreturners.masterofarts.weatheradvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthStatusController {

    @Operation(summary = "Return a basix \"OK\" if the service is up")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkHealth() {
        return new ResponseEntity<>("{ \"STATUS\": \"OK\"}", HttpStatus.OK);
    }
}