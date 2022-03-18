package com.holidayinfo.holidayservice.provider;

import com.holidayinfo.holidayservice.model.Country;
import com.holidayinfo.holidayservice.model.Holiday;
import com.holidayinfo.holidayservice.utils.ConnectionUtil;
import com.holidayinfo.holidayservice.utils.NagerJsonParserUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Slf4j
public class NagerHolidayProvider implements HolidayProvider {

    private String baseUrl;
    private String availableCountriesEndpoint;
    private String publicHolidaysEndpoint;

    @Override
    public List<String> getAvailableCountryCodes() {
        var url = String.format("%s%s", baseUrl, availableCountriesEndpoint);
        try {
            var response = ConnectionUtil.makeGetRequest(url);
            var countryList = NagerJsonParserUtil.parseAvailableCountries(response);
            return countryList.stream()
                    .map(Country::getCountryCode)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Exception while parsing available country list. {}. Request url: {}", e.getMessage(), url);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Holiday> getHolidayForCountryCodeInYear(String countryCode, int year){
        try {
            var url = String.format("%s%s/%d/%s", baseUrl, publicHolidaysEndpoint, year, countryCode);
            log.info("Getting Holiday data for url: {}", url);
            var response = ConnectionUtil.makeGetRequest(url);
            return NagerJsonParserUtil.parseHolidays(response);
        } catch (Exception e) {
            log.error("Exception while parsing available country list. {}.", e.getMessage());
            return Collections.emptyList();
        }
    }
}