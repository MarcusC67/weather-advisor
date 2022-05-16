package com.techreturners.masterofarts.weatheradvisor.recommender;

import com.techreturners.masterofarts.weatheradvisor.model.Recommendation;
import com.techreturners.masterofarts.weatheradvisor.model.Weather;

public interface Recommender {

    Recommendation recommend(Weather weather);
}
