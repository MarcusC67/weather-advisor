package com.techreturners.masterofarts.weatheradvisor.recommender;

import com.techreturners.masterofarts.weatheradvisor.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ShortsRecommenderTest {

    private static final Item expectedItem = Item.Shorts;

    private ShortsRecommender shortsRecommender;

    @BeforeEach
    public void setUp(){
        shortsRecommender = new ShortsRecommender();
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            100,0,Yes
            100,49,Yes
            100,50,Maybe
            25,0,Yes
            25,49,Yes
            25,50,Maybe
            24,0,Maybe
            24,49,Maybe
            24,50,No
            17,0,Maybe
            17,49,Maybe
            17,50,No
            16,0,No
            16,49,No
            16,50,No
            0,0,No
            0,49,No
            0,50,No
            """)
    public void testRecommend(double temp,int cloud, String expectedAdvice) {
        Recommendation recommendation = shortsRecommender.recommend(buildWeather(temp, cloud));

        assertEquals(expectedItem, recommendation.getItem());
        assertEquals(Advice.valueOf(expectedAdvice), recommendation.getAdvice());
    }


    /**
     * Utility function to build a weather object for the given rain
     */
    private Weather buildWeather(double temp, int cloud) {

        Location location = Location.builder()
                .name("London")
                .countryCode("GB")
                .lat(51.509865)
                .lon(-0.118092)
                .build();

        return Weather.builder()
                .location(location)
                .temp(temp)
                .rain(0)
                .cloud(cloud)
                .build();
    }


}