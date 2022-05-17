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
     * Location name
     */
    @Schema(description = "Location name of the given weather")
    String locationName;
  
    /**
     * Country code
     */
    @Schema(description = "Country code of the given weather location")
    String countryCode;
  
    /**
     * Latitude of location
     */
    @Schema(description = "Latitude of given weather location")
    double lat;
  
    /**
     * Longitude of location
     */
    @Schema(description = "Longitude of given weather location")
    double lon;
   
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
     * Current cloud cover as %
     */
    @Schema(description = "Current cloud cover as %")
    int cloud;

}
