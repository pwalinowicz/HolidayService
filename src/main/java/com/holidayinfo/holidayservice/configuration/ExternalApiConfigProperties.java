package com.holidayinfo.holidayservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ext-holiday-api")
public class ExternalApiConfigProperties {
    private String provider;
    private String baseUrl;
    private String availableCountriesEndpoint;
    private String publicHolidaysEndpoint;
}
