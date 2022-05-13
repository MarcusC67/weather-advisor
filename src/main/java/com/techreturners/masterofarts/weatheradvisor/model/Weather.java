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

    double temp;
    long rain;
    int cloud;

}
