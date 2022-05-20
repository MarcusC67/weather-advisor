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
public class OpenApiLocationDeserializerList extends JsonDeserializer<OpenApiLocationList> {

    @Override
    public OpenApiLocationList deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        //Get root node
        JsonNode locationNode = jsonParser.getCodec().readTree(jsonParser);

        if (locationNode.isEmpty()) throw new LocationNotFoundException();

        OpenApiLocationList list = new OpenApiLocationList();

        boolean more = true;
        int index = 0;
        while (more){
            OpenApiLocation openApiLocation = OpenApiLocationDeserializer.getOpenApiLocation(locationNode, index);
            more = (openApiLocation != null);
            if (more)
                list.getList().add(openApiLocation);
            index++;
        }
        return list;
    }
}
