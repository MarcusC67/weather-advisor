package com.techreturners.masterofarts.weatheradvisor.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
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
    List<Recommendation> recommendations;

}
