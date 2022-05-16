package com.techreturners.masterofarts.weatheradvisor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.masterofarts.weatheradvisor.model.Advice;
import com.techreturners.masterofarts.weatheradvisor.model.Location;
import com.techreturners.masterofarts.weatheradvisor.model.Recommendation;
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

import java.util.ArrayList;

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
        double lat = 51.5072;
        double lon = -0.1276;

        Weather weather = Weather
                .builder()
                .cloud(cloud)
                .temp(temp)
                .rain(rain)
                .build();

        when(advisorService.getWeather(lat, lon)).thenReturn(weather);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/weather/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.temp").value(temp))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rain").value(rain))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cloud").value(cloud));
    }

    @Test
    public void testDefaultGetAdvice() throws Exception {

        double lat = 51.5072;
        double lon = -0.1276;
        Location location = Location.builder().name("London").country("GB").lat(lat).lon(lon).build();
        Advice advice = Advice.builder().item(Advice.AdviceItem.Sunscreen).advice(Advice.WeatherAdvice.Yes).build();
        ArrayList<Advice> arrayList = new ArrayList<>();
        arrayList.add(advice);

        Recommendation recommendation = Recommendation
                .builder()
                .location(location)
                .recommendations(arrayList)
                .build();

        when(advisorService.getAdvice(lat, lon)).thenReturn(recommendation);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/recommend/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.lat").value(lat))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.lon").value(lon))
                .andExpect(MockMvcResultMatchers.jsonPath("$.recommendations[0].item").value(Advice.AdviceItem.Sunscreen.toString()));
    }

    @Test
    public void testGetAdviceForLocation() throws Exception {

        double lat = 51.5072;
        double lon = -0.1276;
        Location location = Location.builder().name("London").country("GB").lat(lat).lon(lon).build();
        Advice advice = Advice.builder().item(Advice.AdviceItem.Sunscreen).advice(Advice.WeatherAdvice.Yes).build();
        ArrayList<Advice> arrayList = new ArrayList<>();
        arrayList.add(advice);

        Recommendation recommendation = Recommendation
                .builder()
                .location(location)
                .recommendations(arrayList)
                .build();

        when(advisorService.getAdvice("London")).thenReturn(recommendation);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/recommend/London"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.lat").value(lat))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.lon").value(lon))
                .andExpect(MockMvcResultMatchers.jsonPath("$.recommendations[0].item").value(Advice.AdviceItem.Sunscreen.toString()));
    }

}