package com.techreturners.masterofarts.weatheradvisor.repository;

import com.techreturners.masterofarts.weatheradvisor.model.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenWeatherAPIServiceTest {

    @Autowired
    private OpenWeatherAPIServiceImp openWeatherAPIServiceImp;

    @Test
    public void testGetWeather() {
        Weather w = openWeatherAPIServiceImp.getWeather();
        System.out.println(w);
    }


}