package com.techreturners.masterofarts.weatheradvisor.service;

import com.techreturners.masterofarts.weatheradvisor.model.Advice;
import com.techreturners.masterofarts.weatheradvisor.model.Item;
import com.techreturners.masterofarts.weatheradvisor.model.Recommendation;
import com.techreturners.masterofarts.weatheradvisor.model.Weather;
import org.springframework.stereotype.Component;

@Component
public class UmbrellaRecommender implements Recommender {

    private static final Item item = Item.UMBRELLA;
    private static final double NO_RAIN = 0; //mm of rain per hour
    private static final double LIGHT_RAIN_BOUNDARY = 2.5; //mm of rain per hour

    @Override
    public Recommendation recommend(Weather weather) {

        Advice advice = null;

        if(weather.getRain() <= NO_RAIN)
            advice = Advice.NO;
        else if(weather.getRain() > 0 || weather.getRain() < LIGHT_RAIN_BOUNDARY)
            advice = Advice.MAYBE;
        else if(weather.getRain() > LIGHT_RAIN_BOUNDARY)
            advice = Advice.YES;

        return Recommendation.builder().item(item).advice(advice).build();
    }
}
