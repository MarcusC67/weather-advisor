package com.techreturners.masterofarts.weatheradvisor.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Encapsulation of the advice")

public class Advice {

    public enum WeatherAdvice {
        Yes,
        No,
        Maybe
    }

    public enum AdviceItem {
        Sunscreen,
        Umbrella,
        WoollyHat
    }

    /**
     * Name of item
     */
    @Schema(description = "Name of item")
    AdviceItem item;

    /**
     * Advice for the item
     */
    @Schema(description = "Advice for the item")
    WeatherAdvice advice;

}
