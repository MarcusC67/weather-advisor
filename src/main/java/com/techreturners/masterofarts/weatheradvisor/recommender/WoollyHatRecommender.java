package com.techreturners.masterofarts.weatheradvisor.recommender;

import com.techreturners.masterofarts.weatheradvisor.model.Advice;
import com.techreturners.masterofarts.weatheradvisor.model.Item;
import com.techreturners.masterofarts.weatheradvisor.model.Recommendation;
import com.techreturners.masterofarts.weatheradvisor.model.Weather;
import org.springframework.stereotype.Component;

/**
 * Creates a recommendation for Item.WoollyHat
 */
@Component
public class WoollyHatRecommender implements Recommender {

    private static final Item item = Item.WoollyHat;
    private static final double COLD_BOUNDARY = 5; //in degrees
    private static final double FREEZING_BOUNDARY = 0; //in degrees

    @Override
    public Recommendation recommend(Weather weather) {

        Advice advice = null;

        if (weather.getTemp() > COLD_BOUNDARY)
            advice = Advice.No;
        else if (weather.getTemp() <= COLD_BOUNDARY && weather.getTemp() > FREEZING_BOUNDARY)
            advice = Advice.Maybe;
        else if (weather.getTemp() <= FREEZING_BOUNDARY)
            advice = Advice.Yes;

        return Recommendation.builder()
                .item(item)
                .advice(advice)
                .build();
    }
}
