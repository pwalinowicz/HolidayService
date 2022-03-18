package com.holidayinfo.holidayservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "custom")
public class CustomHolidayServiceConfigProperties {
    private int maxNumberOfYearsToCheck;
}
