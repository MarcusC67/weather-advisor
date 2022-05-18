package com.techreturners.masterofarts.weatheradvisor.error.openweather;

import org.springframework.core.log.LogFormatUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MyResponseErrorHandler extends DefaultResponseErrorHandler {

    protected String getErrorMessage(int rawStatusCode, String statusText, @Nullable byte[] responseBody, @Nullable Charset charset) {
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
