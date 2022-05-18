package com.techreturners.masterofarts.weatheradvisor.error.openweather;

import org.springframework.core.log.LogFormatUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

/**
 * Response Error Handler for when a call is made to the Open Weather Current Weather API
 */
public class CurrentWeatherResponseErrorHandler implements ResponseErrorHandler {


    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (response.getStatusCode().series() == CLIENT_ERROR
                        || response.getStatusCode().series() == SERVER_ERROR);
    }


    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        HttpStatus statusCode = HttpStatus.resolve(response.getRawStatusCode());
        String statusText = response.getStatusText();
        HttpHeaders headers = response.getHeaders();
        byte[] body = this.getResponseBody(response);
        Charset charset = this.getCharset(response);
        String message = this.getErrorMessage(statusCode.value(), statusText, body, charset);

        throw new OpenWeatherAPIException(message, statusCode, statusText, headers, body, charset);
    }

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

    protected byte[] getResponseBody(ClientHttpResponse response) {
        try {
            return FileCopyUtils.copyToByteArray(response.getBody());
        } catch (IOException var3) {
            return new byte[0];
        }
    }

    @Nullable
    protected Charset getCharset(ClientHttpResponse response) {
        HttpHeaders headers = response.getHeaders();
        MediaType contentType = headers.getContentType();
        return contentType != null ? contentType.getCharset() : null;
    }
}
