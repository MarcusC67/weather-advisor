package com.techreturners.masterofarts.weatheradvisor.model;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.techreturners.masterofarts.weatheradvisor.error.LocationNotFoundException;

import java.io.IOException;

/**
 * Deserializes a JSON returned from the external OpenWeather API to an OpenApiWeather object
 */
public class OpenApiLocationDeserializer extends JsonDeserializer<OpenApiLocation> {

    @Override
    public OpenApiLocation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        //Get root node
        JsonNode locationNode = jsonParser.getCodec().readTree(jsonParser);

        if (locationNode.isEmpty()) throw new LocationNotFoundException();
        int index = 0;
        return getOpenApiLocation(locationNode, index);
    }

    protected static OpenApiLocation getOpenApiLocation(JsonNode locationNode, int index) {
        OpenApiLocation location = null;

        //Check required Json keys exist
        //Then mapped to OpenApiLocationObject
        //deserialize location name
        if (locationNode.has(index)) {

            JsonNode firstElement = locationNode.get(index);
            location = new OpenApiLocation();

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
