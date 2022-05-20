package com.techreturners.masterofarts.weatheradvisor.recommender;

import com.techreturners.masterofarts.weatheradvisor.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WoollyHatRecommenderTest {

    private static final Item expectedItem = Item.WoollyHat;

    private WoollyHatRecommender woollyHatRecommender;

    @BeforeEach
    public void setUp() {
        woollyHatRecommender = new WoollyHatRecommender();
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            100,No
            40,No
            5.0000001,No
            5,Maybe
            1,Maybe
            0.000000001,Maybe
            0,Yes
            -0.0000001,Yes
            -5,Yes
            -100,Yes
            """)
    public void testRecommend(double temp, String expectedAdvice) {
        Recommendation recommendation = woollyHatRecommender.recommend(buildWeather(temp));

        assertEquals(expectedItem, recommendation.getItem());
        assertEquals(Advice.valueOf(expectedAdvice), recommendation.getAdvice());
    }


    /**
     * Utility function to build a weather object for the given rain
     */
    private Weather buildWeather(double temp) {

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
                .cloud(75)
                .build();
    }

}