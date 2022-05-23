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
            0,0,No
            6,0,No
            20,0,No
            0,0.00001,Maybe
            0,1,Maybe
            6,1,Maybe
            20,1,No
            0,2.4999,Maybe        
            0,2.5,Yes
            0,3,Yes
            6.5,3,Maybe
            10,3,No
            """)
    public void testRecommend(double wind, double rain, String expectedAdvice) {
        Recommendation recommendation = umbrellaRecommender.recommend(buildWeather(wind, rain));

        assertEquals(expectedItem, recommendation.getItem());
        assertEquals(Advice.valueOf(expectedAdvice), recommendation.getAdvice());
    }


    @ParameterizedTest
    @CsvSource(textBlock = """
            0,-0.00000001
            5,-0.1
            6,-1
            10,-10
            """)
    public void testRecommendWithNegativeRainfall(double wind, double rain){
        Recommendation recommendation = umbrellaRecommender.recommend(buildWeather(wind,rain));

        assertEquals(expectedItem, recommendation.getItem());
        assertNull(recommendation.getAdvice());
    }


    @ParameterizedTest
    @CsvSource(textBlock = """
            -0.0000001,0
            -1,1
            -10,5
            """)
    public void testRecommendWithNegativeWind(double wind,double rain){
        Recommendation recommendation = umbrellaRecommender.recommend(buildWeather(wind,rain));

        assertEquals(expectedItem, recommendation.getItem());
        assertNull(recommendation.getAdvice());
    }

    /**
     * Utility function to build a weather object for the given rain
     */
    private Weather buildWeather(double wind, double rain) {

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
                .wind(wind)
                .build();
    }
}