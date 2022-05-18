package com.techreturners.masterofarts.weatheradvisor.error.openweather;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.nio.charset.Charset;

public class OpenWeatherAPIException extends HttpStatusCodeException {

    public OpenWeatherAPIException(HttpStatus statusCode) {
        super(statusCode);
    }

    public OpenWeatherAPIException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

    public OpenWeatherAPIException(HttpStatus statusCode, String statusText, byte[] responseBody, Charset responseCharset) {
        super(statusCode, statusText, responseBody, responseCharset);
    }

    public OpenWeatherAPIException(HttpStatus statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {
        super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
    }

    public OpenWeatherAPIException(String message, HttpStatus statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {
        super(message, statusCode, statusText, responseHeaders, responseBody, responseCharset);
    }
}
