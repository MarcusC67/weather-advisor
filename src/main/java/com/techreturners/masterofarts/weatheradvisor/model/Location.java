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
@Schema(description = "Encapsulation of the location")

public class Location {

    /**
     * Name of the location
     */
    @Schema(description = "Name of the location")
    String name;

    /**
     * Country
     */
    @Schema(description = "Country code of the location")
    String country;

    /**
     * Longitude
     */
    @Schema(description = "Longitude of the location")
    Double lon;

    /**
     * Latitude
     */
    @Schema(description = "Latitude of the location")
    Double lat;

}
