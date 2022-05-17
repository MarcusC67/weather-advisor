package com.techreturners.masterofarts.weatheradvisor.service;

import com.techreturners.masterofarts.weatheradvisor.model.AdviceForLocation;
import com.techreturners.masterofarts.weatheradvisor.model.Weather;
import com.techreturners.masterofarts.weatheradvisor.repository.ExternalWeatherAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvisorService {

    @Autowired
    private ExternalWeatherAPIService externalWeatherAPIService;

    public Weather getWeather(double lat, double lon) {
        return externalWeatherAPIService.getWeather();
    }

    public Weather getWeather(String location) {

        double lat = 51.5072;
        double lon = -0.1276;

        return getWeather(lat, lon);
    }

    public AdviceForLocation getAdvice(double lat, double lon) {
        return null;
    }

    public AdviceForLocation getAdvice(String location) {

        double lat = 51.5072;
        double lon = -0.1276;

        return getAdvice(lat, lon);
    }
}
