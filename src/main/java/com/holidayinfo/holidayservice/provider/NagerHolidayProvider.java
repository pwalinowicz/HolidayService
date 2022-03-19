package com.holidayinfo.holidayservice.provider;

import com.holidayinfo.holidayservice.model.Country;
import com.holidayinfo.holidayservice.model.Holiday;
import com.holidayinfo.holidayservice.utils.ConnectionUtil;
import com.holidayinfo.holidayservice.utils.HolidayJsonParser;
import com.holidayinfo.holidayservice.utils.NagerJsonParser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Slf4j
public class NagerHolidayProvider implements HolidayProvider {

    private String baseUrl;
    private String availableCountriesEndpoint;
    private String publicHolidaysEndpoint;
    private final HolidayJsonParser parser = new NagerJsonParser();

    public NagerHolidayProvider(String baseUrl, String availableCountriesEndpoint, String publicHolidaysEndpoint) {
        this.baseUrl = baseUrl;
        this.availableCountriesEndpoint = availableCountriesEndpoint;
        this.publicHolidaysEndpoint = publicHolidaysEndpoint;
    }

    @Override
    public List<String> getAvailableCountryCodes() {
        try {
            var url = String.format("%s%s", baseUrl, availableCountriesEndpoint);
            log.info("Getting Available country codes from the url: {}", url);
            var response = ConnectionUtil.makeGetRequest(url);
            if(response.statusCode() == 200) {
                var countryList = parser.parseAvailableCountries(response.body());
                return countryList.stream()
                        .map(Country::getCountryCode)
                        .collect(Collectors.toList());
            } else {
                throw new Exception("Getting available countries failed. Nager API returned status: " + response.statusCode() + ". Body: " + response.body());
            }
        } catch (Exception e) {
            log.error("Exception while parsing available country list. {}.", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Holiday> getHolidayForCountryCodeInYear(String countryCode, int year){
        try {
            var url = String.format("%s%s/%d/%s", baseUrl, publicHolidaysEndpoint, year, countryCode);
            log.info("Getting Holiday data for url: {}", url);
            var response = ConnectionUtil.makeGetRequest(url);
            if(response.statusCode() == 200) {
                return parser.parseHolidays(response.body());
            } else {
                throw new Exception("Getting holidays for country code failed. Nager API returned status: " + response.statusCode() + ". Body: " + response.body());
            }
        } catch (Exception e) {
            log.error("Exception while parsing available country list. {}.", e.getMessage());
            return Collections.emptyList();
        }
    }
}