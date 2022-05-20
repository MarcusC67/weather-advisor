package com.techreturners.masterofarts.weatheradvisor.repository;

import com.techreturners.masterofarts.weatheradvisor.model.Location;
import com.techreturners.masterofarts.weatheradvisor.model.Weather;

import java.util.List;

/**
 * Service that connects to external weather API.
 */
public interface ExternalWeatherAPIService {

    /**
     * Gets the current weather for location 'London' using an External API
     * @return - A Weather object representing the current weather.
     */
    Weather getWeather(double lat, double lon);

    Location getLocationFromName(String name);

    List<Location> getLocationsFromName(String name);

}
