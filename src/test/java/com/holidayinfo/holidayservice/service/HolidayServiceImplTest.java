package com.holidayinfo.holidayservice.service;

import com.holidayinfo.holidayservice.configuration.CustomHolidayServiceConfigProperties;
import com.holidayinfo.holidayservice.configuration.ExternalApiConfigProperties;
import com.holidayinfo.holidayservice.exception.NotSupportedCountryCodeException;
import com.holidayinfo.holidayservice.model.RequestHoliday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HolidayServiceImplTest {

    private final HolidayServiceImpl service = new HolidayServiceImpl();

    ExternalApiConfigProperties properties = new ExternalApiConfigProperties();
    CustomHolidayServiceConfigProperties customProperties = new CustomHolidayServiceConfigProperties();

    @BeforeEach
    public void init() {
        properties.setProvider("nager");
        properties.setBaseUrl("https://date.nager.at/api");
        properties.setAvailableCountriesEndpoint("/v3/AvailableCountries");
        properties.setPublicHolidaysEndpoint("/v3/PublicHolidays");

        customProperties.setMaxNumberOfYearsToCheck(10);
    }

    @Test
    void getCommonHolidayForCorrectRequest() throws NotSupportedCountryCodeException, URISyntaxException, IOException, InterruptedException {
        var request = new RequestHoliday(LocalDate.parse("2002-09-01"), "US", "PL");
        ReflectionTestUtils.setField(service, "properties", properties);
        ReflectionTestUtils.setField(service, "customAppProperties", customProperties);

        var response = service.getCommonHoliday(request);
        assertTrue(response.isPresent());
    }
}