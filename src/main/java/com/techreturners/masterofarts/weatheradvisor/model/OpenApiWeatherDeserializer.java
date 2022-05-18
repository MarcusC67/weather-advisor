package com.techreturners.masterofarts.weatheradvisor.model;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Deserializes a JSON returned from the external OpenWeather API to an OpenApiWeather object
 */
public class OpenApiWeatherDeserializer extends JsonDeserializer {

    @Override
    public OpenApiWeather deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        //Get root node
        JsonNode weatherNode = jsonParser.getCodec().readTree(jsonParser);

        OpenApiWeather weather = new OpenApiWeather();

        //Check required Json keys exist
        //Then mapped to OpenApiWeatherObject
        //deserialize location name
        if(weatherNode.has("name"))
            weather.setLocationName(weatherNode.get("name").asText());

        //deserialize country code
        if(weatherNode.has("sys") && weatherNode.get("sys").has("country"))
            weather.setCountryCode(weatherNode.get("sys").get("country").asText());

        //deserialize lat lon
        if(weatherNode.has("coord")){
            JsonNode coordNode = weatherNode.get("coord");
            if(coordNode.has("lat"))
                weather.setLat(coordNode.get("lat").asDouble());
            if(coordNode.has("lon"))
                weather.setLon(coordNode.get("lon").asDouble());
        }

        //deserialize temperature
        if (weatherNode.has("main") && weatherNode.get("main").has("temp"))
            weather.setTemp(weatherNode.get("main").get("temp").asDouble());

        //deserialize rain
        if (weatherNode.has("rain") && weatherNode.get("rain").has("1h"))
            weather.setRain(weatherNode.get("rain").get("1h").asDouble());

        //deserialize cloud
        if (weatherNode.has("clouds") && weatherNode.get("clouds").has("all"))
            weather.setCloud(weatherNode.get("clouds").get("all").asInt());

        return weather;
    }
}
