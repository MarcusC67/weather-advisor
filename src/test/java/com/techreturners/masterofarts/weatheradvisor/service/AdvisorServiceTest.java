package com.techreturners.masterofarts.weatheradvisor.service;

import com.techreturners.masterofarts.weatheradvisor.model.*;
import com.techreturners.masterofarts.weatheradvisor.recommender.Recommender;
import com.techreturners.masterofarts.weatheradvisor.recommender.UmbrellaRecommender;
import com.techreturners.masterofarts.weatheradvisor.repository.ExternalWeatherAPIService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdvisorServiceTest {

    @Mock
    private ExternalWeatherAPIService mockExternalWeatherAPIService;

    @Mock
    private UmbrellaRecommender mockUmbrellaRecommender;

    @Spy
    private ArrayList<Recommender> recommenders;

    @InjectMocks
    private AdvisorService advisorService;

    @Test
    public void testGetWeather() {
        double temp = 17.3;
        long rain = 0L;
        int cloud = 75;
        Weather weather = Weather
                .builder()
                .cloud(cloud)
                .temp(temp)
                .rain(rain)
                .build();
        when(mockExternalWeatherAPIService.getWeather()).thenReturn(weather);

        assertThat(advisorService.getWeather()).isEqualTo(weather);
    }

    @Test
    public void testGetAdvice() {


        //Arrange
        recommenders.add(mockUmbrellaRecommender);

        Location location = Location.builder()
                .name("London")
                .countryCode("GB")
                .lat(51.509865)
                .lon(-0.118092)
                .build();

        Weather weather = Weather.builder()
                .location(location)
                .temp(17.5)
                .rain(0.5)
                .cloud(75)
                .build();

        Recommendation recommendation = Recommendation.builder()
                .item(Item.Umbrella)
                .advice(Advice.Maybe)
                .build();

        when(mockExternalWeatherAPIService.getWeather()).thenReturn(weather);
        when(mockUmbrellaRecommender.recommend(weather)).thenReturn(recommendation);

        //Act
        AdviceForLocation actualAdviceForLocation = advisorService.getAdvice();

        //Assert Location is correct
        Location actualLocation = actualAdviceForLocation.getLocation();
        assertEquals(location.getName(), actualLocation.getName());
        assertEquals(location.getCountryCode(), actualLocation.getCountryCode());
        assertEquals(location.getLat(), actualLocation.getLat());
        assertEquals(location.getLon(), actualLocation.getLon());

        //Assert recommendations are correct
        Recommendation actualUmbrellaRecommendation = actualAdviceForLocation.getRecommendations().get(0);
        assertEquals(recommendation.getItem(), actualUmbrellaRecommendation.getItem());
        assertEquals(recommendation.getAdvice(), actualUmbrellaRecommendation.getAdvice());
    }

}