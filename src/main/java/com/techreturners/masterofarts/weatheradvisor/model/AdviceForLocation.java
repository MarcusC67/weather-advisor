package com.techreturners.masterofarts.weatheradvisor.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Encapsulation of the current weather")

public class AdviceForLocation {

    /**
     * Location
     */
    @Schema(description = "Location of the recommendation")
    Location location;

    /**
     * Recommendations
     */
    @Schema(description = "Recommendations for the location")
    ArrayList <Recommendation> recommendations;


}
