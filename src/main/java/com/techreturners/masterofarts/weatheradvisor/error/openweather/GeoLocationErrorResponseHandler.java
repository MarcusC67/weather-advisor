package com.techreturners.masterofarts.weatheradvisor.error.openweather;

import com.techreturners.masterofarts.weatheradvisor.error.ExternalAPIException;
import com.techreturners.masterofarts.weatheradvisor.error.LocationNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;

public class GeoLocationErrorResponseHandler extends MyResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        HttpStatus statusCode = HttpStatus.resolve(response.getRawStatusCode());
        String statusText = response.getStatusText();
        HttpHeaders headers = response.getHeaders();
        byte[] body = this.getResponseBody(response);
        Charset charset = this.getCharset(response);
        String message = this.getErrorMessage(statusCode.value(), statusText, body, charset);

        if(statusCode == HttpStatus.NOT_FOUND)
            throw new LocationNotFoundException();
        else
            throw new ExternalAPIException(message, statusCode, statusText, headers, body, charset);
    }

}
