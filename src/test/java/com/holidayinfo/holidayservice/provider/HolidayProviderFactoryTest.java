package com.holidayinfo.holidayservice.provider;

import com.holidayinfo.holidayservice.configuration.ExternalApiConfigProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HolidayProviderFactoryTest {

    @Test
    void isGettingNagerProviderForCorrectConfiguration(){
        var properties = new ExternalApiConfigProperties();
        properties.setProvider("nager");
        var provider = HolidayProviderFactory.getProvider(properties);
        assertTrue(provider.isPresent());
        assertTrue(provider.get() instanceof NagerHolidayProvider);
    }

    @Test
    void isGettingEmptyProviderForIncorrectConfiguration(){
        var properties = new ExternalApiConfigProperties();
        properties.setProvider("non-existing provider");
        var provider = HolidayProviderFactory.getProvider(properties);
        assertTrue(provider.isEmpty());
    }

}