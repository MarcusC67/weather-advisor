package com.techreturners.masterofarts.weatheradvisor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.masterofarts.weatheradvisor.model.*;
import com.techreturners.masterofarts.weatheradvisor.service.AdvisorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
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
import java.util.List;

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

    @ParameterizedTest
    @ValueSource(strings = {"/", "/London/", "", "/London"})
    public void testGetWeatherForLocation(String endpoint) throws Exception {

        double lat = 51.5072;
        double lon = -0.1276;
        String name = "London";
        String countryCode = "GB";
        Location location = Location.builder().name(name).countryCode(countryCode).lat(lat).lon(lon).build();

        double temp = 17.3;
        long rain = 0L;
        int cloud = 75;

        Weather weather = Weather
                .builder()
                .location(location)
                .cloud(cloud)
                .temp(temp)
                .rain(rain)
                .build();

        // have to do both as may go through either route and mock implements nothing unless you set it!
        when(advisorService.getWeather(location.getName())).thenReturn(weather);
        when(advisorService.getWeather(lat, lon)).thenReturn(weather);

        String urlTemplate = "/api/v1/weather" + endpoint;

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get(urlTemplate))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.lat").value(lat))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.lon").value(lon))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temp").value(temp))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rain").value(rain))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cloud").value(cloud));
    }

    @Test
    public void testGetWeatherForLatLon() throws Exception {

        double lat = 51.5072;
        double lon = -0.1276;
        String name = "London";
        String countryCode = "GB";
        Location location = Location.builder().name(name).countryCode(countryCode).lat(lat).lon(lon).build();

        double temp = 17.3;
        long rain = 0L;
        int cloud = 75;

        Weather weather = Weather
                .builder()
                .location(location)
                .cloud(cloud)
                .temp(temp)
                .rain(rain)
                .build();

        when(advisorService.getWeather(lat, lon)).thenReturn(weather);

        String urlTemplate = "/api/v1/weather/";

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get(urlTemplate)
                                .param("lat", Double.toString(lat))
                                .param("lon", Double.toString(lon)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.lat").value(lat))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.lon").value(lon))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temp").value(temp))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rain").value(rain))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cloud").value(cloud));
    }

    @Test
    public void testDefaultGetAdviceForLatLon() throws Exception {

        double lat = 51.5072;
        double lon = -0.1276;
        Location location = Location.builder().name("London").countryCode("GB").lat(lat).lon(lon).build();
        Recommendation recommendation = Recommendation.builder().item(Item.Umbrella).advice(Advice.Yes).build();
        ArrayList<Recommendation> arrayList = new ArrayList<>();
        arrayList.add(recommendation);

        AdviceForLocation adviceForLocation = AdviceForLocation
                .builder()
                .location(location)
                .recommendations(arrayList)
                .build();

        when(advisorService.getAdvice(lat, lon)).thenReturn(adviceForLocation);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/recommend/")
                        .param("lat", Double.toString(lat))
                        .param("lon", Double.toString(lon)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.lat").value(lat))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.lon").value(lon))
                .andExpect(MockMvcResultMatchers.jsonPath("$.recommendations[0].item").value(Item.Umbrella.toString()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/", "/London/", "", "/London"})
    public void testGetAdviceForLocation(String endpoint) throws Exception {

        double lat = 51.5072;
        double lon = -0.1276;
        Location location = Location.builder().name("London").countryCode("GB").lat(lat).lon(lon).build();
        Recommendation recommendation = Recommendation.builder().item(Item.Umbrella).advice(Advice.Yes).build();
        ArrayList<Recommendation> arrayList = new ArrayList<>();
        arrayList.add(recommendation);

        AdviceForLocation adviceForLocation = AdviceForLocation
                .builder()
                .location(location)
                .recommendations(arrayList)
                .build();

        // have to do both as may go through either route and mock implements nothing unless you set it!
        when(advisorService.getAdvice(lat, lon)).thenReturn(adviceForLocation);
        when(advisorService.getAdvice("London")).thenReturn(adviceForLocation);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/recommend/London"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.lat").value(lat))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.lon").value(lon))
                .andExpect(MockMvcResultMatchers.jsonPath("$.recommendations[0].item").value(Item.Umbrella.toString()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/", "/London/", "", "/London"})
    public void findLocation(String endpoint) throws Exception {
        double lat = 51.5072;
        double lon = -0.1276;
        String name = "London";
        String countryCode = "GB";
        Location location = Location.builder().name(name).countryCode(countryCode).lat(lat).lon(lon).build();

        double lat2 = 42.9836747;
        double lon2 = -81.2496068;
        String countryCode2 = "CA";
        Location location2 = Location.builder().name(name).countryCode(countryCode2).lat(lat2).lon(lon2).build();

        List<Location> list = new ArrayList<>();
        list.add(location);
        list.add(location2);
        when(advisorService.findLocation("London")).thenReturn(list);

        String urlTemplate = "/api/v1/find" + endpoint;
        this.mockMvcController.perform(
                MockMvcRequestBuilders.get(urlTemplate))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString())) // for testing to see JSON
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lat").value(lat))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lon").value(lon))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].countryCode").value(countryCode))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lat").value(lat2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lon").value(lon2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].countryCode").value(countryCode2));
    }
}