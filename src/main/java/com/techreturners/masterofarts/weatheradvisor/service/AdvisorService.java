package com.techreturners.masterofarts.weatheradvisor.service;

import com.techreturners.masterofarts.weatheradvisor.model.*;
import com.techreturners.masterofarts.weatheradvisor.repository.ExternalWeatherAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvisorService {

    @Autowired
    private ExternalWeatherAPIService externalWeatherAPIService;

    @Autowired
    private List<Recommender> recommenders;

    public Weather getWeather(){
        return externalWeatherAPIService.getWeather();
    }

    public AdviceObject getAdvice(){
        Weather weather = externalWeatherAPIService.getWeather();

        Location location = Location.builder()
                                    .name(weather.getLocationName())
                                    .countryCode(weather.getCountryCode())
                                    .lat(weather.getLat())
                                    .lon(weather.getLon()).build();


        List<Recommendation> recommendations = new ArrayList<>();
        for(Recommender recommender : recommenders){
            recommendations.add(recommender.recommend(weather));
        }

        return AdviceObject.builder().location(location).recommendations(recommendations).build();
    }
}
