package com.techreturners.masterofarts.weatheradvisor.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * An error response object which is returned to the client upon an error
 */
@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private int rawStatusCode;
    private String statusCode;
    private String message;
}
