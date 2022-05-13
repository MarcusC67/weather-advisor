package com.techreturners.masterofarts.weatheradvisor.repository;

import com.techreturners.masterofarts.weatheradvisor.model.Weather;

public interface ExternalWeatherAPIService {

    /**
     * Gets the current weather for location 'London' using an External API
     * @return - A Weather object representing the current weather.
     */
    Weather getWeather();
}
