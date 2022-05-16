package com.techreturners.masterofarts.weatheradvisor.model;

import lombok.Builder;

@Builder
public class Location {

    String name;
    String countryCode;
    double lat;
    double lon;

}
