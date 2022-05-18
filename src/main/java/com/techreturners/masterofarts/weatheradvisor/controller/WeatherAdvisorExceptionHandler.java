package com.techreturners.masterofarts.weatheradvisor.controller;

import com.techreturners.masterofarts.weatheradvisor.error.ErrorResponse;
import com.techreturners.masterofarts.weatheradvisor.error.ExternalAPIException;
import com.techreturners.masterofarts.weatheradvisor.error.LocationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handles all exceptions within the WeatherAdvisor API
 */
@ControllerAdvice
public class WeatherAdvisorExceptionHandler {

    @ExceptionHandler({ExternalAPIException.class})
    public ResponseEntity<ErrorResponse> handleTestException(ExternalAPIException e) {

        ErrorResponse response = ErrorResponse.builder()
                .rawStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .message("Internal Server Error. Please contact API Admins.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({LocationNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleTestException(LocationNotFoundException e) {

        ErrorResponse response = ErrorResponse.builder()
                                              .rawStatusCode(HttpStatus.NOT_FOUND.value())
                                              .statusCode(HttpStatus.NOT_FOUND.toString())
                .message("Location not found")
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
