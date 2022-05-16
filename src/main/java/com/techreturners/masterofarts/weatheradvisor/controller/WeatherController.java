package com.techreturners.masterofarts.weatheradvisor.controller;

import com.techreturners.masterofarts.weatheradvisor.model.Weather;
import com.techreturners.masterofarts.weatheradvisor.service.AdvisorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class WeatherController {

    @Autowired
    // injects the dependency into the Weather Controller
    AdvisorService advisorService;

    @Operation(summary = "Get current weather for given location, or default to London")
    @GetMapping({"/weather"})
    public ResponseEntity<Weather> getWeather(
            @RequestParam(required = false, defaultValue = "51.5072") double lat,
            @RequestParam(required = false, defaultValue = "-0.1276") double lon){
        Weather weather = advisorService.getWeather(lat, lon);
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }

    @Operation(summary = "Get current weather for specific location")
    @GetMapping({"/weather/{location}"})
    public ResponseEntity<Weather> getWeather(
            @Parameter(description = "location to get weather for", example = "London")
            @PathVariable String location){
        Weather weather = advisorService.getWeather(location);
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }

}
