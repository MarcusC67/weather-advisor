package com.techreturners.masterofarts.weatheradvisor.repository.openweatherapi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = OpenApiLocationDeserializer.class)
public class OpenApiLocation {

    String name;
    String countryCode;
    double lat;
    double lon;

}
