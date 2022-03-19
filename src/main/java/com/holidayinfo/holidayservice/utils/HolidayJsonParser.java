package com.holidayinfo.holidayservice.utils;

import com.holidayinfo.holidayservice.model.Country;
import com.holidayinfo.holidayservice.model.Holiday;

import java.util.List;

public interface HolidayJsonParser {
    List<Country> parseAvailableCountries(String response);
    List<Holiday> parseHolidays(String response);
}
