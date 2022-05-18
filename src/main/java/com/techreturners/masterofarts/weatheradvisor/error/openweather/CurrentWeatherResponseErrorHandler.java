package com.techreturners.masterofarts.weatheradvisor.error.openweather;

import com.techreturners.masterofarts.weatheradvisor.error.ExternalAPIException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Response Error Handler for when a call is made to the Open Weather Current Weather API
 */
public class CurrentWeatherResponseErrorHandler extends MyResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        HttpStatus statusCode = HttpStatus.resolve(response.getRawStatusCode());
        String statusText = response.getStatusText();
        HttpHeaders headers = response.getHeaders();
        byte[] body = this.getResponseBody(response);
        Charset charset = this.getCharset(response);
        String message = this.getErrorMessage(statusCode.value(), statusText, body, charset);

        throw new ExternalAPIException(message, statusCode, statusText, headers, body, charset);
    }
}

