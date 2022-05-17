package com.techreturners.masterofarts.weatheradvisor.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Schema(description = "Encapsulation of the advice")
public class Recommendation {

    /**
     * Name of item
     */
    @Schema(description = "Name of item")
    Item item;

    /**
     * Advice for the item
     */
    @Schema(description = "Advice for the item")
    Advice advice;

}
