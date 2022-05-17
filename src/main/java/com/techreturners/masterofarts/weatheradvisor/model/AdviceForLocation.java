package com.techreturners.masterofarts.weatheradvisor.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AdviceForLocation {

    Location location;
    List<Recommendation> recommendations;


}
