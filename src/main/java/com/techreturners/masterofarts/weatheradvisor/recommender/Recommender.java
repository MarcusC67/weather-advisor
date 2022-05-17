package com.techreturners.masterofarts.weatheradvisor.recommender;

import com.techreturners.masterofarts.weatheradvisor.model.Recommendation;
import com.techreturners.masterofarts.weatheradvisor.model.Weather;

/**
 * Functional Interface that returns a recommendation for a given weather
 */
public interface Recommender {

    Recommendation recommend(Weather weather);
}
