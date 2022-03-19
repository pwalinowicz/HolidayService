package com.holidayinfo.holidayservice.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NagerJsonParserTest {

    private NagerJsonParser parser = new NagerJsonParser();

    @Test
    void parsesCorrectlyAvailableCoutryList(){
        //GIVEN
        String availableCountriesJson= "[\n" +
                "    {\n" +
                "        \"countryCode\": \"AD\",\n" +
                "        \"name\": \"Andorra\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"countryCode\": \"AL\",\n" +
                "        \"name\": \"Albania\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"countryCode\": \"AR\",\n" +
                "        \"name\": \"Argentina\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"countryCode\": \"AT\",\n" +
                "        \"name\": \"Austria\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"countryCode\": \"AU\",\n" +
                "        \"name\": \"Australia\"\n" +
                "    }]";

        //WHEN
        var parsedList = parser.parseAvailableCountries(availableCountriesJson);
        //THEN
        assertEquals(5, parsedList.size());
    }

    @Test
    void parsesMalformedJsonToEmptyCountryList(){
        //GIVEN
        String wrongAvailableCountriesJson= "null";

        //WHEN
        var parsedList = parser.parseAvailableCountries(wrongAvailableCountriesJson);
        //THEN
        assertEquals(0, parsedList.size());
    }

    @Test
    void parsesIncorrectJsonToEmptyCountryList(){
        //GIVEN
        String wrongAvailableCountriesJson= "[\n" +
                "    {\n" +
                "        \"wrong\": \"wrong\",\n" +
                "        \"wrong\": \"wrong\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"wrong\": \"wrong\",\n" +
                "        \"wrong\": \"wrong\",\n" +
                "        \"wrong\": \"wrong\",\n" +
                "        \"wrong\": \"wrong\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"wrong\": \"wrong\",\n" +
                "        \"wrong\": \"wrong\"\n" +
                "        \"wrong\": \"wrong\"\n" +
                "    }]";

        //WHEN
        var parsedList = parser.parseAvailableCountries(wrongAvailableCountriesJson);
        //THEN
        assertEquals(0, parsedList.size());
    }

    @Test
    void parsesCorrectlyHolidayList(){
        //GIVEN
        String holidayJson= "[{\n" +
                "        \"date\": \"2120-01-01\",\n" +
                "        \"localName\": \"New Year's Day\",\n" +
                "        \"name\": \"New Year's Day\",\n" +
                "        \"countryCode\": \"US\",\n" +
                "        \"fixed\": false,\n" +
                "        \"global\": true,\n" +
                "        \"counties\": null,\n" +
                "        \"launchYear\": null,\n" +
                "        \"types\": [\n" +
                "            \"Public\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"date\": \"2120-01-15\",\n" +
                "        \"localName\": \"Martin Luther King, Jr. Day\",\n" +
                "        \"name\": \"Martin Luther King, Jr. Day\",\n" +
                "        \"countryCode\": \"US\",\n" +
                "        \"fixed\": false,\n" +
                "        \"global\": true,\n" +
                "        \"counties\": null,\n" +
                "        \"launchYear\": null,\n" +
                "        \"types\": [\n" +
                "            \"Public\"\n" +
                "        ]\n" +
                "    }]";

        //WHEN
        var parsedList = parser.parseHolidays(holidayJson);
        //THEN
        assertEquals(2, parsedList.size());
    }

    @Test
    void parsesMalformedJsonToEmptyHolidayList(){
        //GIVEN
        String holidayJson= "null";

        //WHEN
        var parsedList = parser.parseHolidays(holidayJson);
        //THEN
        assertEquals(0, parsedList.size());
    }

    @Test
    void parsesIncorrectJsonToEmptyHolidayList(){
        //GIVEN
        String holidayJson= "[\n" +
                "    {\n" +
                "        \"wrong\": \"wrong\",\n" +
                "        \"wrong\": \"wrong\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"wrong\": \"wrong\",\n" +
                "        \"wrong\": \"wrong\",\n" +
                "        \"wrong\": \"wrong\",\n" +
                "        \"wrong\": \"wrong\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"wrong\": \"wrong\",\n" +
                "        \"wrong\": \"wrong\"\n" +
                "        \"wrong\": \"wrong\"\n" +
                "    }]";

        //WHEN
        var parsedList = parser.parseHolidays(holidayJson);
        //THEN
        assertEquals(0, parsedList.size());
    }
}