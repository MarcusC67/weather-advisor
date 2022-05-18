package com.techreturners.masterofarts.weatheradvisor.controller;

import com.techreturners.masterofarts.weatheradvisor.error.ErrorResponse;
import com.techreturners.masterofarts.weatheradvisor.error.openweather.OpenWeatherAPIException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Handles all exceptions within the WeatherAdvisor API
 */
@ControllerAdvice
public class WeatherAdvisorExceptionHandler {

    @ExceptionHandler({OpenWeatherAPIException.class})
    public ResponseEntity<ErrorResponse> handleTestException(OpenWeatherAPIException e) {

        ErrorResponse response = ErrorResponse.builder()
                .rawStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .message("Internal Server Error. Please contact API Admins.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
