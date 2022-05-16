package com.techreturners.masterofarts.weatheradvisor.recommender;

import com.techreturners.masterofarts.weatheradvisor.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UmbrellaRecommenderTest {

    private static final Item expectedItem = Item.Umbrella;

    private UmbrellaRecommender umbrellaRecommender;

    @BeforeEach
    public void setUp(){
        umbrellaRecommender = new UmbrellaRecommender();
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            0,No
            0.5,Maybe
            1,Maybe
            2.4999,Maybe
            2.5,Yes
            3,Yes
            10,Yes
            100,Yes
            """)
    public void testRecommend(double rain, String expectedAdvice) {
        Recommendation recommendation = umbrellaRecommender.recommend(buildWeather(rain));

        assertEquals(expectedItem, recommendation.getItem());
        assertEquals(Advice.valueOf(expectedAdvice), recommendation.getAdvice());
    }


    @ParameterizedTest
    @CsvSource(textBlock = """
            -0.00000001
            -0.1
            -1
            -10
            """)
    public void testRecommendWithNegativeRainfall(double rain){
        Recommendation recommendation = umbrellaRecommender.recommend(buildWeather(rain));

        assertEquals(expectedItem, recommendation.getItem());
        assertNull(recommendation.getAdvice());
    }

    /**
     * Utility function to build a weather object for the given rain
     */
    private Weather buildWeather(double rain) {

        Location location = Location.builder()
                .name("London")
                .countryCode("GB")
                .lat(51.509865)
                .lon(-0.118092)
                .build();

        return Weather.builder()
                .location(location)
                .temp(17.5)
                .rain(rain)
                .cloud(75)
                .build();
    }
}