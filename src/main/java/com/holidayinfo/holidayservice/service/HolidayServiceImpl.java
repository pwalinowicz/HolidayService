package com.holidayinfo.holidayservice.service;

import com.holidayinfo.holidayservice.configuration.CustomHolidayServiceConfigProperties;
import com.holidayinfo.holidayservice.configuration.ExternalApiConfigProperties;
import com.holidayinfo.holidayservice.exception.NotSupportedCountryCodeException;
import com.holidayinfo.holidayservice.model.Holiday;
import com.holidayinfo.holidayservice.model.RequestHoliday;
import com.holidayinfo.holidayservice.model.ResultHoliday;
import com.holidayinfo.holidayservice.provider.HolidayProvider;
import com.holidayinfo.holidayservice.provider.HolidayProviderFactory;
import com.holidayinfo.holidayservice.utils.HolidayParserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class HolidayServiceImpl implements HolidayService{

    @Autowired
    private ExternalApiConfigProperties properties;

    @Autowired
    private CustomHolidayServiceConfigProperties customAppProperties;

    @Override
    public Optional<ResultHoliday> getCommonHoliday(RequestHoliday request) throws NotSupportedCountryCodeException, URISyntaxException, IOException, InterruptedException {
        var providerOptional = HolidayProviderFactory.getProvider(properties);
        if(providerOptional.isPresent()){
            var provider = providerOptional.get();
            var countryCodes = parseRequestToCountryCodeList(request);
            checkCountryCodesAvailability(provider, countryCodes);
            return getNextCommonHoliday(provider, request);
        } else {
            throw new IllegalArgumentException("Holiday Provider is not present");
        }
    }

    private ArrayList<String> parseRequestToCountryCodeList(RequestHoliday request) {
        var countryCodes = new ArrayList<String>();
        countryCodes.add(request.getCountryCode1());
        countryCodes.add(request.getCountryCode2());
        return countryCodes;
    }

    private void checkCountryCodesAvailability(HolidayProvider provider, List<String> countryCodes)
            throws URISyntaxException, IOException, InterruptedException, NotSupportedCountryCodeException {
        var availableCountryCodes = provider.getAvailableCountryCodes();
        for (var code: countryCodes) {
            if(!availableCountryCodes.contains(code)){
                throw new NotSupportedCountryCodeException(code);
            }
        }
    }

    private Optional<ResultHoliday> getNextCommonHoliday(HolidayProvider provider, RequestHoliday request) {
        var count = 0;
        var year = request.getDate().getYear();
        Optional<LocalDate> commonDateOptional = Optional.empty();
        List<Holiday> firstCountryCodeHolidays = new ArrayList<>();
        List<Holiday> secondCountryCodeHolidays = new ArrayList<>();

        while(count < customAppProperties.getMaxNumberOfYearsToCheck() && commonDateOptional.isEmpty()){
            var yearToCheck = year + count;

            firstCountryCodeHolidays = provider.getHolidayForCountryCodeInYear(request.getCountryCode1(), yearToCheck);
            var filteredFirstCountryHolidays = HolidayParserUtil.getHolidaysAfterDate(request.getDate(), firstCountryCodeHolidays);

            secondCountryCodeHolidays = provider.getHolidayForCountryCodeInYear(request.getCountryCode2(), yearToCheck);
            var filteredSecondCountryHolidays = HolidayParserUtil.getHolidaysAfterDate(request.getDate(), secondCountryCodeHolidays);

            commonDateOptional = HolidayParserUtil.getFirstCommonDateInHolidayLists(filteredFirstCountryHolidays, filteredSecondCountryHolidays);
            count++;
        }

        return commonDateOptional.isPresent() ?
                HolidayParserUtil.getOptionalResultHoliday(commonDateOptional.get(), firstCountryCodeHolidays, secondCountryCodeHolidays):
                Optional.empty();
    }
}

