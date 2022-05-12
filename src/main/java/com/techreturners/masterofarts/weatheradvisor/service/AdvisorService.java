package com.techreturners.masterofarts.weatheradvisor.service;

import com.techreturners.masterofarts.weatheradvisor.model.Weather;
import com.techreturners.masterofarts.weatheradvisor.repository.ExternalWeatherAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvisorService {

    @Autowired
    private ExternalWeatherAPIService externalWeatherAPIService;

    public Weather getWeather(){
        return externalWeatherAPIService.getWeather();
    }
}
