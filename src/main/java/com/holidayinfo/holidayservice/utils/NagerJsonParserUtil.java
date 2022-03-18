package com.holidayinfo.holidayservice.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.holidayinfo.holidayservice.model.Country;
import com.holidayinfo.holidayservice.model.Holiday;
import com.holidayinfo.holidayservice.model.NagerHoliday;
import lombok.experimental.UtilityClass;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@UtilityClass
public class NagerJsonParserUtil {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class,
                    (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString())).create();

    public static List<Country> parseAvailableCountries(HttpResponse<String> response) {
        var body = response.body();
        var listType = new TypeToken<ArrayList<Country>>(){}.getType();
        return GSON.fromJson(body, listType);
    }

    public static List<Holiday> parseHolidays(HttpResponse<String> response) {
        var body = response.body();
        var listType = new TypeToken<ArrayList<NagerHoliday>>(){}.getType();
        return GSON.fromJson(body, listType);
    }
}
