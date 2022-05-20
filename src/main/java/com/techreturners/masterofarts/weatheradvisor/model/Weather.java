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
@Schema(description = "Encapsulation of the current weather")
public class Weather {

    /**
     * Location of the given weather
     */
    @Schema(description = "The Location of the given weather")
    Location location;

    /**
     * Current temperature in degrees C
     */
    @Schema(description = "Current temperature in degrees C")
    double temp;

    /**
     * Rainfall in last hour in mm
     */
    @Schema(description = "Rainfall in last hour in mm")
    double rain;

    /**
     * Wind speed in meters/sec
     */
    @Schema(description = "Wind speed in meters/sec")
    double wind;

    /**
     * Current cloud cover as %
     */
    @Schema(description = "Current cloud cover as %")
    int cloud;

}
