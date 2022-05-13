package com.techreturners.masterofarts.weatheradvisor.controller;

import com.techreturners.masterofarts.weatheradvisor.model.Weather;
import com.techreturners.masterofarts.weatheradvisor.service.AdvisorService;
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

    @GetMapping({"/weather"})
    public ResponseEntity<Weather> getWeather(){
        Weather weather = advisorService.getWeather();
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }
}
