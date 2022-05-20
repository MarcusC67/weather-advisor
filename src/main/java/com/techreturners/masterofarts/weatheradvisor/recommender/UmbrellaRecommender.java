package com.techreturners.masterofarts.weatheradvisor.recommender;

import com.techreturners.masterofarts.weatheradvisor.model.Advice;
import com.techreturners.masterofarts.weatheradvisor.model.Item;
import com.techreturners.masterofarts.weatheradvisor.model.Recommendation;
import com.techreturners.masterofarts.weatheradvisor.model.Weather;
import org.springframework.stereotype.Component;

/**
 * Creates a recommendation for Item.Umbrella
 */
@Component
public class UmbrellaRecommender implements Recommender {

    private static final Item item = Item.Umbrella;
    private static final double NO_RAIN = 0; //mm of rain per hour
    private static final double LIGHT_RAIN = 2.5; //mm of rain per hour
    private static final double VERY_WINDY = 7; //in meters / second
    private static final double SLIGHTLY_WINDY = 5.5; //in meters / second

    @Override
    public Recommendation recommend(Weather weather) {

        Advice advice = null;
        double wind = weather.getWind();
        double rain = weather.getRain();

        if(wind < 0 || rain < 0 ) //invalid wind or rain values
            advice = null;
        else if(wind >= VERY_WINDY || rain == NO_RAIN) //windy or no rain
            advice = Advice.No;
        else if( (wind < VERY_WINDY && wind >= SLIGHTLY_WINDY) || (rain > NO_RAIN && rain < LIGHT_RAIN)) //slightly windy or light rain
            advice = Advice.Maybe;
        else if(rain >= LIGHT_RAIN) //Heavy rain and less than light wind
            advice = Advice.Yes;

        return Recommendation.builder().item(item).advice(advice).build();
    }
}
