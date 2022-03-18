package com.holidayinfo.holidayservice.service;

import com.holidayinfo.holidayservice.exception.NotSupportedCountryCodeException;
import com.holidayinfo.holidayservice.model.RequestHoliday;
import com.holidayinfo.holidayservice.model.ResultHoliday;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public interface HolidayService {
    Optional<ResultHoliday> getCommonHoliday(RequestHoliday request) throws NotSupportedCountryCodeException, URISyntaxException, IOException, InterruptedException;
}