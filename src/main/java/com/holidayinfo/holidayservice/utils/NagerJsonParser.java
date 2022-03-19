package com.holidayinfo.holidayservice.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.holidayinfo.holidayservice.model.Country;
import com.holidayinfo.holidayservice.model.Holiday;
import com.holidayinfo.holidayservice.model.NagerCountry;
import com.holidayinfo.holidayservice.model.NagerHoliday;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

@Slf4j
@NoArgsConstructor
public class NagerJsonParser implements HolidayJsonParser {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class,
                    (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString())).create();

    public List<Country> parseAvailableCountries(String response) {
        var listType = new TypeToken<ArrayList<NagerCountry>>() {}.getType();
        return parseListFromJsonByType(response, listType);
    }

    public List<Holiday> parseHolidays(String response) {
        var listType = new TypeToken<ArrayList<NagerHoliday>>() {}.getType();
        return parseListFromJsonByType(response, listType);
    }

    private <T> List<T> parseListFromJsonByType(String response, Type listType) {
        try {
            List<T> parsedList = GSON.fromJson(response, listType);
            if(parsedList != null){
                return parsedList;
            } else {
                throw new NullPointerException("Malformed Json returned null list");
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            log.error("Error while parsing Json response: {}. Exception: {}", response, e.getMessage());
            return Collections.emptyList();
        }
    }
}
