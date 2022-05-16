package com.techreturners.masterofarts.weatheradvisor.model;

import lombok.Builder;

import java.util.List;

@Builder
public class AdviceForLocation {

    Location location;
    List<Recommendation> recommendations;


}
