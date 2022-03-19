package com.holidayinfo.holidayservice.provider;

import com.holidayinfo.holidayservice.configuration.ExternalApiConfigProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HolidayProviderFactoryTest {

    @Test
    void isGettingNagerProviderForCorrectConfiguration(){
        //GIVEN
        var properties = new ExternalApiConfigProperties();
        properties.setProvider("nager");
        //WHEN
        var provider = HolidayProviderFactory.getProvider(properties);
        //THEN
        assertTrue(provider.isPresent());
        assertTrue(provider.get() instanceof NagerHolidayProvider);
    }

    @Test
    void isGettingEmptyProviderForIncorrectConfiguration(){
        //GIVEN
        var properties = new ExternalApiConfigProperties();
        properties.setProvider("non-existing provider");
        //WHEN
        var provider = HolidayProviderFactory.getProvider(properties);
        //THEN
        assertTrue(provider.isEmpty());
    }

}