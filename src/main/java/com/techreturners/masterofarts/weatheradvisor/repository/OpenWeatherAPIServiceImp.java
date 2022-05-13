package com.techreturners.masterofarts.weatheradvisor.repository;

import com.techreturners.masterofarts.weatheradvisor.model.OpenApiWeather;
import com.techreturners.masterofarts.weatheradvisor.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherAPIServiceImp implements ExternalWeatherAPIService {


    private static final String URL = "https://api.openweathermap.org/data/2.5/weather?appid=%s&lat=%s&lon=%s&units=%s";
    private static final String UNITS = "metric";
    private static final double LAT = 51.509865;
    private static final double LON = -0.118092;

    @Value("${openapi.key}")
    private String API_KEY;

    private RestTemplate restTemplate;

    @Autowired
    public OpenWeatherAPIServiceImp(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }


    @Override
    public Weather getWeather() {
        //Api response deserialized into OpenAPI WeatherObject
        OpenApiWeather openApiWeather = restTemplate.getForObject(
                String.format(URL, API_KEY, LAT, LON, UNITS),
                OpenApiWeather.class
        );

        //OpenApiWeather mapped to Weather Model
        return Weather.builder()
                      .temp(openApiWeather.getTemp())
                      .rain(openApiWeather.getRain())
                      .cloud(openApiWeather.getCloud())
                      .build();
    }
}
