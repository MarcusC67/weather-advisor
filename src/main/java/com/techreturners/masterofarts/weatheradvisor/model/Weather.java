package com.techreturners.masterofarts.weatheradvisor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    double temp;
    long rain;
    int cloud;

}
