package com.holidayinfo.holidayservice.provider;

import com.holidayinfo.holidayservice.configuration.ExternalApiConfigProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class HolidayProviderFactory {

    private static final String NAGER_PROVIDER_NAME = "nager";

    public static Optional<HolidayProvider> getProvider(ExternalApiConfigProperties properties) {
        if (properties != null) {
            var providerName = properties.getProvider().toLowerCase();
            switch (providerName) {
                case NAGER_PROVIDER_NAME:
                    log.info("Creating the NagerHolidayProvider");
                    return Optional.of(new NagerHolidayProvider(properties.getBaseUrl(),
                            properties.getAvailableCountriesEndpoint(),
                            properties.getPublicHolidaysEndpoint()));
                default:
                    log.error("No valid provider properties available in the ExternalApiConfigProperties. HolidayProvider was not created.");
                    return Optional.empty();
            }
        }
        return Optional.empty();
    }
}