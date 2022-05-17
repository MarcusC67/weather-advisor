package com.techreturners.masterofarts.weatheradvisor.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Encapsulation of a Location")
public class Location {

    /**
     * Location name
     */
    @Schema(description = "Location name")
    String name;

    /**
     * Country code
     */
    @Schema(description = "Country code of location")
    String countryCode;

    /**
     * Latitude of location
     */
    @Schema(description = "Latitude of location")
    double lat;

    /**
     * Longitude of location
     */
    @Schema(description = "Longitude of location")
    double lon;

}
