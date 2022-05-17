package com.techreturners.masterofarts.weatheradvisor.service;

import com.techreturners.masterofarts.weatheradvisor.model.*;
import com.techreturners.masterofarts.weatheradvisor.recommender.Recommender;
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

    public AdviceForLocation getAdvice(){
        Weather weather = externalWeatherAPIService.getWeather();

        //use all the recommenders to create recommendations for the given weather
        List<Recommendation> recommendations = new ArrayList<>();
        for(Recommender recommender : recommenders){
            recommendations.add(recommender.recommend(weather));
        }

        return AdviceForLocation.builder().location(weather.getLocation()).recommendations(recommendations).build();
    }
}
