package com.techreturners.masterofarts.weatheradvisor.error;

import com.techreturners.masterofarts.weatheradvisor.error.ExternalAPIException;
import org.springframework.core.log.LogFormatUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Response Error Handler for when a call is made to the Open Weather API
 */
public class OpenWeatherResponseErrorHandler extends DefaultResponseErrorHandler {


    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        //Create parameters to build exception
        HttpStatus statusCode = HttpStatus.resolve(response.getRawStatusCode());
        String statusText = response.getStatusText();
        HttpHeaders headers = response.getHeaders();
        byte[] body = this.getResponseBody(response);
        Charset charset = this.getCharset(response);
        String message = this.getErrorMessage(statusCode.value(), statusText, body, charset);

        //throw error
        throw new ExternalAPIException(message, statusCode, statusText, headers, body, charset);
    }

    /**
     * From DefaultResponseErrorHandler
     */
    private String getErrorMessage(int rawStatusCode, String statusText, @Nullable byte[] responseBody, @Nullable Charset charset) {
        String preface = rawStatusCode + " " + statusText + ": ";
        if (ObjectUtils.isEmpty(responseBody)) {
            return preface + "[no body]";
        } else {
            charset = charset != null ? charset : StandardCharsets.UTF_8;
            String bodyText = new String(responseBody, charset);
            bodyText = LogFormatUtils.formatValue(bodyText, -1, true);
            return preface + bodyText;
        }
    }
}

