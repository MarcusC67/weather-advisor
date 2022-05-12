package com.techreturners.masterofarts.weatheradvisor.model;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class OpenApiDeserializer extends JsonDeserializer {

    @Override
    public OpenApiWeather deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        JsonNode weatherNode = jsonParser.getCodec().readTree(jsonParser);

        OpenApiWeather weather = new OpenApiWeather();

        if (weatherNode.has("main") && weatherNode.get("main").has("temp"))
            weather.setTemp(weatherNode.get("main").get("temp").asDouble());
        if (weatherNode.has("rain") && weatherNode.get("rain").has("1h"))
            weather.setRain(weatherNode.get("rain").get("1h").asLong());
        if (weatherNode.has("clouds") && weatherNode.get("clouds").has("all"))
            weather.setCloud(weatherNode.get("clouds").get("all").asInt());

        return weather;
    }
}
