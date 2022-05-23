package com.techreturners.masterofarts.weatheradvisor.repository.openweatherapi;

import com.techreturners.masterofarts.weatheradvisor.error.OpenWeatherResponseErrorHandler;
import com.techreturners.masterofarts.weatheradvisor.model.*;
import com.techreturners.masterofarts.weatheradvisor.repository.ExternalWeatherAPIService;
import com.techreturners.masterofarts.weatheradvisor.repository.openweatherapi.OpenApiLocation;
import com.techreturners.masterofarts.weatheradvisor.repository.openweatherapi.OpenApiLocationList;
import com.techreturners.masterofarts.weatheradvisor.repository.openweatherapi.OpenApiWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenWeatherAPIServiceImp implements ExternalWeatherAPIService {

    private static final String DOMAIN_URL = "https://api.openweathermap.org/";
    private static final String GEO_ENDPOINT = "geo/1.0/direct?appid=%s&q=%s&limit=%s";
    private static final String WEATHER_ENDPOINT = "data/2.5/weather?appid=%s&lat=%s&lon=%s&units=%s";
    private static final String UNITS = "metric";
    private static final int LIMIT = 1;
    private static final int MAX = 5; // currently, the API only returns a maximum of 5, even if a higher limit is passed

    @Value("${openapi.key}")
    private String API_KEY;

    private RestTemplate restTemplate;

    @Autowired
    public OpenWeatherAPIServiceImp(RestTemplateBuilder builder) {
        this.restTemplate = builder
                .errorHandler(new OpenWeatherResponseErrorHandler())
                .build();
    }

    @Override
    public Weather getWeather(double lat, double lon) {

            //Api response deserialized into OpenAPI WeatherObject
            OpenApiWeather openApiWeather = restTemplate.getForObject(
                    String.format(DOMAIN_URL + WEATHER_ENDPOINT, API_KEY, lat, lon, UNITS),
                    OpenApiWeather.class
            );


            Location location = Location.builder()
                    .name(openApiWeather.getLocationName())
                    .countryCode(openApiWeather.getCountryCode())
                    .lat(openApiWeather.getLat())
                    .lon(openApiWeather.getLon())
                    .build();

            //OpenApiWeather mapped to Weather Model
            return Weather.builder()
                    .location(location)
                    .temp(openApiWeather.getTemp())
                    .rain(openApiWeather.getRain())
                    .wind(openApiWeather.getWind())
                    .cloud(openApiWeather.getCloud())
                    .build();
    }

    @Override
    public Location getLocationFromName(String name) {
        //Api response deserialized into OpenAPI Location Object
        OpenApiLocation openApiLocation = restTemplate.getForObject(
                String.format(DOMAIN_URL + GEO_ENDPOINT, API_KEY, name, LIMIT),
                OpenApiLocation.class
        );

        //OpenApiLocation mapped to Location Model
        return Location.builder()
                .name(openApiLocation.getName())
                .countryCode(openApiLocation.getCountryCode())
                .lat(openApiLocation.getLat())
                .lon(openApiLocation.getLon()).build();
    }

    @Override
    public List<Location> getLocationsFromName(String name) {

        //Api response deserialized into OpenAPI Location Object
        OpenApiLocationList openApiLocationList = restTemplate.getForObject(
                String.format(DOMAIN_URL + GEO_ENDPOINT, API_KEY, name, MAX),
                OpenApiLocationList.class
        );

        List<Location> list = new ArrayList<>();
        for (OpenApiLocation openApiLocation: openApiLocationList.getList()) {
            Location location = Location.builder()
                    .name(openApiLocation.getName())
                    .countryCode(openApiLocation.getCountryCode())
                    .lat(openApiLocation.getLat())
                    .lon(openApiLocation.getLon()).build();
            list.add(location);
        }
        return list;
    }

}
