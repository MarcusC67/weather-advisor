package com.techreturners.masterofarts.weatheradvisor.repository;

import com.techreturners.masterofarts.weatheradvisor.model.Location;
import com.techreturners.masterofarts.weatheradvisor.model.OpenApiLocation;
import com.techreturners.masterofarts.weatheradvisor.model.OpenApiWeather;
import com.techreturners.masterofarts.weatheradvisor.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherAPIServiceImp implements ExternalWeatherAPIService {

    private static final String DOMAIN_URL = "https://api.openweathermap.org/";
    private static final String GEO_ENDPOINT_URL = "geo/1.0/direct?appid=%s&q=%s&limit=%s";
    private static final String WEATHER_ENDPOINT_URL = "data/2.5/weather?appid=%s&lat=%s&lon=%s&units=%s";
    private static final String UNITS = "metric";
    private static final int LIMIT = 1;

    @Value("${openapi.key}")
    private String API_KEY;

    private RestTemplate restTemplate;

    @Autowired
    public OpenWeatherAPIServiceImp(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public Weather getWeather(double lat, double lon) {
        //Api response deserialized into OpenAPI WeatherObject
        OpenApiWeather openApiWeather = restTemplate.getForObject(
                String.format(DOMAIN_URL + WEATHER_ENDPOINT_URL, API_KEY, lat, lon, UNITS),
                OpenApiWeather.class
        );

        Location location = Location.builder()
                .name(openApiWeather.getLocationName())
                .countryCode(openApiWeather.getCountryCode())
                .lat(openApiWeather.getLat())
                .lon(openApiWeather.getLon()).build();

        //OpenApiWeather mapped to Weather Model
        return Weather.builder()
                .location(location)
                .temp(openApiWeather.getTemp())
                .rain(openApiWeather.getRain())
                .cloud(openApiWeather.getCloud())
                .build();
    }

    @Override
    public Location getLocationFromName(String name) {
        //Api response deserialized into OpenAPI WeatherObject
        OpenApiLocation openApiLocation = restTemplate.getForObject(
                String.format(DOMAIN_URL + GEO_ENDPOINT_URL, API_KEY, name, LIMIT),
                OpenApiLocation.class
        );

        //OpenApiLocation mapped to Location Model
        return Location.builder()
                .name(openApiLocation.getName())
                .countryCode(openApiLocation.getCountryCode())
                .lat(openApiLocation.getLat())
                .lon(openApiLocation.getLon()).build();
    }

}
