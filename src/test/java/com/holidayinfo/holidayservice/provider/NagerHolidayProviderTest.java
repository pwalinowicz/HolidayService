package com.holidayinfo.holidayservice.provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NagerHolidayProviderTest {

    NagerHolidayProvider provider;

    @BeforeEach
    void init(){
        provider = new NagerHolidayProvider("https://date.nager.at/api", "/v3/AvailableCountries", "/v3/PublicHolidays");
    }

    @Test
    void isGettingAvailableCountriesForCorrectEndpoint(){
        var list = provider.getAvailableCountryCodes();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    void returns0AvailableCountriesWithWrongUrl(){
        provider = new NagerHolidayProvider("wrongUrl", "wrongUrl", "wrongUrl");
        var list = provider.getAvailableCountryCodes();
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    void isGettingHolidays(){
        var list = provider.getHolidayForCountryCodeInYear("PL", 2021);
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    void returns0HolidaysForWrongCountryCodeAndYear(){
        var list = provider.getHolidayForCountryCodeInYear("XX", 5000);
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    void returns0HolidaysForNullCountryCode(){
        var list = provider.getHolidayForCountryCodeInYear(null, 0);
        assertNotNull(list);
        assertEquals(0, list.size());
    }

}