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
        double lat = 51.5072;
        double lon = -0.1276;
        Weather weather = Weather
                .builder()
                .cloud(cloud)
                .temp(temp)
                .rain(rain)
                .build();
        when(mockExternalWeatherAPIService.getWeather(lat, lon)).thenReturn(weather);

        assertThat(advisorService.getWeather(lat, lon)).isEqualTo(weather);
    }

    @Test
    public void testGetWeatherForLocation() {

        //Arrange
        double temp = 17.3;
        long rain = 0L;
        int cloud = 75;
        double lat = 51.5072;
        double lon = -0.1276;
        String locationName = "London";

        Location location = Location.builder()
                .name(locationName)
                .countryCode("GB")
                .lat(lat)
                .lon(lon)
                .build();

        Weather weather = Weather
                .builder()
                .cloud(cloud)
                .temp(temp)
                .rain(rain)
                .build();

        when(mockExternalWeatherAPIService.getLocationFromName(locationName)).thenReturn(location);
        when(mockExternalWeatherAPIService.getWeather(location.getLat(), location.getLon())).thenReturn(weather);

        //Assert weather returned for location is correct
        assertThat(advisorService.getWeather(locationName)).isEqualTo(weather);
    }

    @Test
    public void testGetAdvice() {

        //Arrange
        double lat = 51.5072;
        double lon = -0.1276;

        recommenders.add(mockUmbrellaRecommender);

        Location location = Location.builder()
                .name("London")
                .countryCode("GB")
                .lat(lat)
                .lon(lon)
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

        List<Recommendation> recommendations = List.of(recommendation);

        AdviceForLocation expected = AdviceForLocation.builder().location(location).recommendations(recommendations).build();

        when(mockExternalWeatherAPIService.getWeather(lat, lon)).thenReturn(weather);
        when(mockUmbrellaRecommender.recommend(weather)).thenReturn(recommendation);

        assertThat(advisorService.getAdvice(lat, lon)).isEqualTo(expected);
    }

    @Test
    public void testGetAdviceForLocation() {

        //Arrange
        String locationName = "London";

        recommenders.add(mockUmbrellaRecommender);

        Location location = Location.builder()
                .name(locationName)
                .countryCode("GB")
                .lat(51.5072)
                .lon(-0.1276)
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

        List<Recommendation> recommendations = List.of(recommendation);

        AdviceForLocation expected = AdviceForLocation.builder().location(location).recommendations(recommendations).build();

        when(mockExternalWeatherAPIService.getLocationFromName(locationName)).thenReturn(location);
        when(mockExternalWeatherAPIService.getWeather(location.getLat(), location.getLon())).thenReturn(weather);
        when(mockUmbrellaRecommender.recommend(weather)).thenReturn(recommendation);

        assertThat(advisorService.getAdvice(locationName)).isEqualTo(expected);

    }

}