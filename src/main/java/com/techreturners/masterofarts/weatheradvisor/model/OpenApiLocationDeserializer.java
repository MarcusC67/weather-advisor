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
public class OpenApiLocationDeserializer extends JsonDeserializer {

    @Override
    public OpenApiLocation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        //Get root node
        JsonNode locationNode = jsonParser.getCodec().readTree(jsonParser);

        OpenApiLocation location = new OpenApiLocation();

        //Check required Json keys exist
        //Then mapped to OpenApiLocationObject
        //deserialize location name
        if (locationNode.has(0)) {

            JsonNode firstElement = locationNode.get(0);

            if (firstElement.has("name"))
                location.setName(firstElement.get("name").asText());

            if (firstElement.has("lat"))
                location.setLat(firstElement.get("lat").asDouble());

            if (firstElement.has("lon"))
                location.setLon(firstElement.get("lon").asDouble());

            if (firstElement.has("country"))
                location.setCountryCode(firstElement.get("country").asText());
        }
        return location;
    }
}
