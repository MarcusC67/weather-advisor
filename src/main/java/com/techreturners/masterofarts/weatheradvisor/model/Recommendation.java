package com.techreturners.masterofarts.weatheradvisor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Recommendation {

    Item item;
    Advice advice;

}
