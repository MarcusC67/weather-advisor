package com.techreturners.masterofarts.weatheradvisor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Weather {

    String locationName;
    String countryCode;
    double lat;
    double lon;
    double temp;
    double rain;
    int cloud;

}
