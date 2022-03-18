package com.holidayinfo.holidayservice.provider;

import com.holidayinfo.holidayservice.model.Holiday;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface HolidayProvider {
    List<String> getAvailableCountryCodes() throws URISyntaxException, IOException, InterruptedException;
    List<Holiday> getHolidayForCountryCodeInYear(String countryCode, int year);
}
