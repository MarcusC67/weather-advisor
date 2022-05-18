package com.techreturners.masterofarts.weatheradvisor.repository;

import com.techreturners.masterofarts.weatheradvisor.error.openweather.CurrentWeatherResponseErrorHandler;
import com.techreturners.masterofarts.weatheradvisor.model.Location;
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

    @Value("${openapi.key}")
    private String API_KEY;

    private RestTemplate currentWeatherRestTemplate;

    @Autowired
    public OpenWeatherAPIServiceImp(RestTemplateBuilder builder) {
        this.currentWeatherRestTemplate = builder.errorHandler(new CurrentWeatherResponseErrorHandler()).build();
    }

    @Override
    public Weather getWeather(double lat, double lon) {

            //Api response deserialized into OpenAPI WeatherObject
            OpenApiWeather openApiWeather = currentWeatherRestTemplate.getForObject(
                    String.format(URL, API_KEY, lat, lon, UNITS),
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
                    .cloud(openApiWeather.getCloud())
                    .build();
    }
}
