package com.techreturners.masterofarts.weatheradvisor.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = OpenApiDeserializer.class)
public class OpenApiWeather {

    String locationName;
    String countryCode;
    double lat;
    double lon;
    double temp;
    double rain;
    int cloud;

}