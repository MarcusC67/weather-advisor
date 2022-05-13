package com.techreturners.masterofarts.weatheradvisor.repository;

import com.techreturners.masterofarts.weatheradvisor.model.Weather;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherAPIServiceImp implements ExternalWeatherAPIService{

    @Override
    public Weather getWeather() {
        return null;
    }
}
