package com.techreturners.masterofarts.weatheradvisor.recommender;

import com.techreturners.masterofarts.weatheradvisor.model.Advice;
import com.techreturners.masterofarts.weatheradvisor.model.Item;
import com.techreturners.masterofarts.weatheradvisor.model.Recommendation;
import com.techreturners.masterofarts.weatheradvisor.model.Weather;
import org.springframework.stereotype.Component;


/**
 * Creates a recommendation for Item.Suncream
 */
@Component
public class ShortsRecommender implements Recommender{

    private static final Item item = Item.Shorts;
    private static final double HOT_TEMP = 25; //in degrees
    private static final double WARM_TEMP = 17; //in degrees
    private static final double PARTIAL_CLOUD_COVER = 50; //as a percentage

    @Override
    public Recommendation recommend(Weather weather) {

        Advice advice = null;
        double temp = weather.getTemp();
        int cloud = weather.getCloud();

        if(temp >= HOT_TEMP && cloud < PARTIAL_CLOUD_COVER)
            advice = Advice.Yes;
        else if(temp >= HOT_TEMP && cloud >= PARTIAL_CLOUD_COVER)
            advice = Advice.Maybe;
        else if(temp < HOT_TEMP && temp >= WARM_TEMP && cloud < PARTIAL_CLOUD_COVER)
            advice = Advice.Maybe;
        else if(temp < HOT_TEMP && temp >= WARM_TEMP && cloud >= PARTIAL_CLOUD_COVER)
            advice = Advice.No;
        else if(temp < WARM_TEMP)
            advice = Advice.No;

        return Recommendation.builder().item(item).advice(advice).build();
    }
}
