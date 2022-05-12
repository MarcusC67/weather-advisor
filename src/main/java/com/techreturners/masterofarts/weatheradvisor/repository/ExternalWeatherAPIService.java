package com.techreturners.masterofarts.weatheradvisor.repository;

import com.techreturners.masterofarts.weatheradvisor.model.Weather;

public interface ExternalWeatherAPIService {

    Weather getWeather();
}
