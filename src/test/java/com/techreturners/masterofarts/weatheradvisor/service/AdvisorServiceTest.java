package com.techreturners.masterofarts.weatheradvisor.service;

import com.techreturners.masterofarts.weatheradvisor.model.Weather;
import com.techreturners.masterofarts.weatheradvisor.repository.ExternalWeatherAPIService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdvisorServiceTest {

    @Mock
    private ExternalWeatherAPIService mockExternalWeatherAPIService;

    @InjectMocks
    private AdvisorService advisorService;

    @Test
    public void testGetWeather(){

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
        when(mockExternalWeatherAPIService.getWeather()).thenReturn(weather);

        assertThat(advisorService.getWeather(lat, lon)).isEqualTo(weather);

    }

}