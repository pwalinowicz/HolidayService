package com.holidayinfo.holidayservice.controller;

import com.holidayinfo.holidayservice.configuration.CustomHolidayServiceConfigProperties;
import com.holidayinfo.holidayservice.configuration.ExternalApiConfigProperties;
import com.holidayinfo.holidayservice.model.RequestHoliday;
import com.holidayinfo.holidayservice.service.HolidayServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class HolidayControllerTest {

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
    void isGettingOKResponseForCorrectRequest(){
        //GIVEN
        ReflectionTestUtils.setField(service, "properties", properties);
        ReflectionTestUtils.setField(service, "customAppProperties", customProperties);
        var request = new RequestHoliday(LocalDate.of(2020, 1,1), "US", "PL");

        //WHEN
        HolidayController controller = new HolidayController(service);
        var response = controller.getCommonHoliday(request);

        //THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void isGettingInternalServerErrorResponseForIncorrectRequest(){
        //GIVEN
        properties.setBaseUrl("£");
        ReflectionTestUtils.setField(service, "properties", properties);
        ReflectionTestUtils.setField(service, "customAppProperties", customProperties);
        var request = new RequestHoliday(LocalDate.of(2020, 1,1), "XX", "YY");

        //WHEN
        HolidayController controller = new HolidayController(service);
        var response = controller.getCommonHoliday(request);

        //THEN
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}