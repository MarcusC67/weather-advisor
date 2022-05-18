package com.techreturners.masterofarts.weatheradvisor.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.nio.charset.Charset;

public class ExternalAPIException extends HttpStatusCodeException {

    public ExternalAPIException(HttpStatus statusCode) {
        super(statusCode);
    }

    public ExternalAPIException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

    public ExternalAPIException(HttpStatus statusCode, String statusText, byte[] responseBody, Charset responseCharset) {
        super(statusCode, statusText, responseBody, responseCharset);
    }

    public ExternalAPIException(HttpStatus statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {
        super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
    }

    public ExternalAPIException(String message, HttpStatus statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {
        super(message, statusCode, statusText, responseHeaders, responseBody, responseCharset);
    }
}
