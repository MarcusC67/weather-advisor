package com.techreturners.masterofarts.weatheradvisor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.masterofarts.weatheradvisor.model.Weather;
import com.techreturners.masterofarts.weatheradvisor.service.AdvisorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
class WeatherControllerTest {

    @Mock
    private AdvisorService advisorService;

    @InjectMocks
    private WeatherController weatherController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(weatherController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testDefaultGetWeather() throws Exception {


        double temp = 17.3;
        long rain = 0L;
        int cloud = 75;
        Weather weather = new Weather(temp, rain, cloud);

        when(advisorService.getWeather()).thenReturn(weather);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/weather/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.temp").value(temp))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rain").value(rain))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cloud").value(cloud));
    }


}