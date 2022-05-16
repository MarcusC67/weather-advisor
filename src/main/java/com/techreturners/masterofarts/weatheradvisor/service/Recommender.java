package com.techreturners.masterofarts.weatheradvisor.service;

import com.techreturners.masterofarts.weatheradvisor.model.Recommendation;
import com.techreturners.masterofarts.weatheradvisor.model.Weather;

public interface Recommender {

    Recommendation recommend(Weather weather);
}
