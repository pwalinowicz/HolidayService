package com.holidayinfo.holidayservice.provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NagerHolidayProviderTest {

    NagerHolidayProvider provider;

    @BeforeEach
    void init(){
        //GIVEN
        provider = new NagerHolidayProvider("https://date.nager.at/api", "/v3/AvailableCountries", "/v3/PublicHolidays");
    }

    @Test
    void isGettingAvailableCountriesForCorrectEndpoint(){
        //WHEN
        var list = provider.getAvailableCountryCodes();
        //THEN
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    void returns0AvailableCountriesWithWrongUrl(){
        //GIVEN
        provider = new NagerHolidayProvider("wrongUrl", "wrongUrl", "wrongUrl");
        //WHEN
        var list = provider.getAvailableCountryCodes();
        //THEN
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    void isGettingHolidays(){
        //WHEN
        var list = provider.getHolidayForCountryCodeInYear("PL", 2021);
        //THEN
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    void returns0HolidaysForWrongCountryCodeAndYear(){
        //WHEN
        var list = provider.getHolidayForCountryCodeInYear("XX", 5000);
        //THEN
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    void returns0HolidaysForNullCountryCode(){
        //WHEN
        var list = provider.getHolidayForCountryCodeInYear(null, 0);
        //THEN
        assertNotNull(list);
        assertEquals(0, list.size());
    }

}