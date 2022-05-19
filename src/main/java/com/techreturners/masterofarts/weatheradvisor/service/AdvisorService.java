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

    public Weather getWeather(double lat, double lon){
        return externalWeatherAPIService.getWeather(lat, lon);
    }

    public Weather getWeather(String location) {

        double lat = 51.5072;
        double lon = -0.1276;

        return getWeather(lat, lon);
    }

    public AdviceForLocation getAdvice(double lat, double lon){

        Weather weather = externalWeatherAPIService.getWeather(lat, lon);

        //use all the recommenders to create recommendations for the given weather
        List<Recommendation> recommendations = new ArrayList<>();
        for(Recommender recommender : recommenders){
            recommendations.add(recommender.recommend(weather));
        }

        return AdviceForLocation.builder().location(weather.getLocation()).recommendations(recommendations).build();
    }

    public AdviceForLocation getAdvice(String location) {

        double lat = 51.5072;
        double lon = -0.1276;

        return getAdvice(lat, lon);
    }

}
