package com.holidayinfo.holidayservice.service;

import com.holidayinfo.holidayservice.configuration.CustomHolidayServiceConfigProperties;
import com.holidayinfo.holidayservice.configuration.ExternalApiConfigProperties;
import com.holidayinfo.holidayservice.exception.NotSupportedCountryCodeException;
import com.holidayinfo.holidayservice.model.RequestHoliday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HolidayServiceImplTest {

    private final HolidayServiceImpl service = new HolidayServiceImpl();

    ExternalApiConfigProperties properties = new ExternalApiConfigProperties();
    CustomHolidayServiceConfigProperties customProperties = new CustomHolidayServiceConfigProperties();

    @BeforeEach
    public void init() {
        //GIVEN
        properties.setProvider("nager");
        properties.setBaseUrl("https://date.nager.at/api");
        properties.setAvailableCountriesEndpoint("/v3/AvailableCountries");
        properties.setPublicHolidaysEndpoint("/v3/PublicHolidays");

        customProperties.setMaxNumberOfYearsToCheck(10);
    }

    @Test
    void isGettingCommonHolidayForCorrectRequest() throws NotSupportedCountryCodeException, URISyntaxException, IOException, InterruptedException {
        //GIVEN
        var request = new RequestHoliday(LocalDate.parse("2002-12-30"), "US", "PL");
        ReflectionTestUtils.setField(service, "properties", properties);
        ReflectionTestUtils.setField(service, "customAppProperties", customProperties);
        //WHEN
        var response = service.getCommonHoliday(request);
        //THEN
        assertTrue(response.isPresent());
        assertEquals(LocalDate.of(2003,1,1), response.get().getDate());
    }

    @Test
    void isThrowingExceptionIfCountryCodeIsNotAvailable() throws NotSupportedCountryCodeException, URISyntaxException, IOException, InterruptedException {
        //GIVEN
        var request = new RequestHoliday(LocalDate.parse("2002-09-01"), "YYY", "PL");
        ReflectionTestUtils.setField(service, "properties", properties);
        ReflectionTestUtils.setField(service, "customAppProperties", customProperties);
        //THEN
        assertThrows(NotSupportedCountryCodeException.class, () -> {
            service.getCommonHoliday(request);
        });
    }

    @Test
    void isThrowingExceptionIfConfiguredProviderIsNotAvaiable() throws NotSupportedCountryCodeException, URISyntaxException, IOException, InterruptedException {
        //GIVEN
        var request = new RequestHoliday(LocalDate.parse("2002-09-01"), "YYY", "PL");
        properties.setProvider("WRONGPROVIDER");
        ReflectionTestUtils.setField(service, "properties", properties);
        ReflectionTestUtils.setField(service, "customAppProperties", customProperties);
        //THEN
        assertThrows(IllegalArgumentException.class, () -> {
            service.getCommonHoliday(request);
        });
    }
}