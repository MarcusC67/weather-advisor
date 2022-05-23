package com.techreturners.masterofarts.weatheradvisor.repository.openweatherapi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = OpenApiLocationDeserializerList.class)
public class OpenApiLocationList {

    List<OpenApiLocation> list = new ArrayList<>();

}
