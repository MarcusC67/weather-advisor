package com.techreturners.masterofarts.weatheradvisor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Weather {

    double temp;
    long rain;
    int cloud;

}
