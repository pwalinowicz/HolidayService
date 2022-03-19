package com.holidayinfo.holidayservice.utils;

import com.holidayinfo.holidayservice.model.Holiday;
import com.holidayinfo.holidayservice.model.NagerHoliday;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HolidayParserUtilTest {

    @Test
    void isFindingFirstCommonDateCorrectHolidayLists() {
        //GIVEN
        var holiday1 = new NagerHoliday(LocalDate.parse("2020-01-01"), "New Year's Day", "New Year's Day", "US", false, true, 0,  new String[]{"Public"});
        var holiday2 = new NagerHoliday(LocalDate.parse("2020-12-25"), "Christmas Day", "Christmas Day", "US", false, true, 0,  new String[]{"Public"});
        var holiday3 = new NagerHoliday(LocalDate.parse("2020-01-01"), "Nowy Rok", "New Year's Day", "PL", false, true, 0,  new String[]{"Public"});
        var holiday4 = new NagerHoliday(LocalDate.parse("2020-01-06"), "Święto Trzech Króli", "Epiphany", "PL", false, true, 0,  new String[]{"Public"});
        var holiday5 = new NagerHoliday(LocalDate.parse("2020-12-25"), "Boże Narodzenie", "Christmas Day", "PL", false, true, 0,  new String[]{"Public"});
        var list1 = new ArrayList<Holiday>();
        list1.add(holiday1);
        list1.add(holiday2);
        var list2 = new ArrayList<Holiday>();
        list2.add(holiday3);
        list2.add(holiday4);
        list2.add(holiday5);

        //WHEN
        var commonLocalDate = HolidayParserUtil.getFirstCommonDateInHolidayLists(list1, list2);

        //THEN
        assertTrue(commonLocalDate.isPresent());
        assertEquals(LocalDate.of(2020,1,1), commonLocalDate.get());
    }

    @Test
    void isGivingEmptyOptionalWhenNoCommonDateFound() {
        //GIVEN
        var holiday1 = new NagerHoliday(LocalDate.parse("2020-01-02"), "New Year's Day", "New Year's Day", "US", false, true, 0,  new String[]{"Public"});
        var holiday2 = new NagerHoliday(LocalDate.parse("2020-12-25"), "Christmas Day", "Christmas Day", "US", false, true, 0,  new String[]{"Public"});
        var holiday3 = new NagerHoliday(LocalDate.parse("2020-01-01"), "Nowy Rok", "New Year's Day", "PL", false, true, 0,  new String[]{"Public"});
        var holiday4 = new NagerHoliday(LocalDate.parse("2020-01-06"), "Święto Trzech Króli", "Epiphany", "PL", false, true, 0,  new String[]{"Public"});
        var list1 = new ArrayList<Holiday>();
        list1.add(holiday1);
        list1.add(holiday2);
        var list2 = new ArrayList<Holiday>();
        list2.add(holiday3);
        list2.add(holiday4);

        //WHEN
        var commonLocalDate = HolidayParserUtil.getFirstCommonDateInHolidayLists(list1, list2);

        //THEN
        assertTrue(commonLocalDate.isEmpty());
    }

    @Test
    void isGivingEmptyOptionalForEmptyLists() {
        //GIVEN
        var list1 = new ArrayList<Holiday>();
        var list2 = new ArrayList<Holiday>();

        //WHEN
        var commonLocalDate = HolidayParserUtil.getFirstCommonDateInHolidayLists(list1, list2);

        //THEN
        assertTrue(commonLocalDate.isEmpty());
    }

    @Test
    void isGivingEmptyOptionalForNullLists() {
        //GIVEN
        List<Holiday> list1 = null;
        List<Holiday> list2 = null;

        //WHEN
        var commonLocalDate = HolidayParserUtil.getFirstCommonDateInHolidayLists(list1, list2);

        //THEN
        assertTrue(commonLocalDate.isEmpty());
    }

    @Test
    void isGettingCorectHolidaysAfterOrEqualDate() {
        //GIVEN
        var holiday1 = new NagerHoliday(LocalDate.parse("2020-01-02"), "New Year's Day", "New Year's Day", "US", false, true, 0,  new String[]{"Public"});
        var holiday2 = new NagerHoliday(LocalDate.parse("2020-12-25"), "Christmas Day", "Christmas Day", "US", false, true, 0,  new String[]{"Public"});
        var list1 = new ArrayList<Holiday>();
        list1.add(holiday1);
        list1.add(holiday2);
        var localDate = LocalDate.of(2020,11,4);
        //WHEN
        var listHolidayAfterOrEqual = HolidayParserUtil.getHolidaysAfterOrEqualDate(localDate,list1);
        //THEN
        assertEquals(1, listHolidayAfterOrEqual.size());
    }

    @Test
    void isGettingEmptyListForNoAfterOrEqualDate() {
        //GIVEN
        var holiday1 = new NagerHoliday(LocalDate.parse("2020-01-02"), "New Year's Day", "New Year's Day", "US", false, true, 0,  new String[]{"Public"});
        var holiday2 = new NagerHoliday(LocalDate.parse("2020-12-25"), "Christmas Day", "Christmas Day", "US", false, true, 0,  new String[]{"Public"});
        var list1 = new ArrayList<Holiday>();
        list1.add(holiday1);
        list1.add(holiday2);
        var localDate = LocalDate.of(2020,12,30);
        //WHEN
        var listHolidayAfterOrEqual = HolidayParserUtil.getHolidaysAfterOrEqualDate(localDate,list1);
        //THEN
        assertEquals(0, listHolidayAfterOrEqual.size());
    }

    @Test
    void isGettingCorrectOptionalResultHoliday() {
        //GIVEN
        var holiday1 = new NagerHoliday(LocalDate.parse("2020-01-01"), "New Year's Day", "New Year's Day", "US", false, true, 0,  new String[]{"Public"});
        var holiday2 = new NagerHoliday(LocalDate.parse("2020-12-25"), "Christmas Day", "Christmas Day", "US", false, true, 0,  new String[]{"Public"});
        var holiday3 = new NagerHoliday(LocalDate.parse("2020-01-01"), "Nowy Rok", "New Year's Day", "PL", false, true, 0,  new String[]{"Public"});
        var holiday4 = new NagerHoliday(LocalDate.parse("2020-01-06"), "Święto Trzech Króli", "Epiphany", "PL", false, true, 0,  new String[]{"Public"});
        var holiday5 = new NagerHoliday(LocalDate.parse("2020-12-25"), "Boże Narodzenie", "Christmas Day", "PL", false, true, 0,  new String[]{"Public"});
        var list1 = new ArrayList<Holiday>();
        list1.add(holiday1);
        list1.add(holiday2);
        var list2 = new ArrayList<Holiday>();
        list2.add(holiday3);
        list2.add(holiday4);
        list2.add(holiday5);
        var commonLocalDate = LocalDate.of(2020,12,25);
        //WHEN

        var resultHoliday = HolidayParserUtil.getOptionalResultHoliday(commonLocalDate, list1, list2);

        //THEN
        assertTrue(resultHoliday.isPresent());
        assertEquals(commonLocalDate, resultHoliday.get().getDate());
        assertEquals("Christmas Day", resultHoliday.get().getName1());
        assertEquals("Boże Narodzenie", resultHoliday.get().getName2());
    }

    @Test
    void isGettinEmptyOptionalResultHolidayIfEmptyList() {
        //GIVEN
        var holiday1 = new NagerHoliday(LocalDate.parse("2020-01-01"), "New Year's Day", "New Year's Day", "US", false, true, 0,  new String[]{"Public"});
        var holiday2 = new NagerHoliday(LocalDate.parse("2020-12-25"), "Christmas Day", "Christmas Day", "US", false, true, 0,  new String[]{"Public"});
        var list1 = new ArrayList<Holiday>();
        list1.add(holiday1);
        list1.add(holiday2);
        var list2 = new ArrayList<Holiday>();
        var commonLocalDate = LocalDate.of(2020,12,25);
        //WHEN
        var resultHoliday = HolidayParserUtil.getOptionalResultHoliday(commonLocalDate, list1, list2);
        //THEN
        assertTrue(resultHoliday.isEmpty());
    }

    @Test
    void isGettinEmptyOptionalResultHolidayIfNull() {
        //GIVEN
        List<Holiday> list1 = null;
        List<Holiday> list2 = null;
        var commonLocalDate = LocalDate.of(2020,12,25);
        //WHEN
        var resultHoliday = HolidayParserUtil.getOptionalResultHoliday(commonLocalDate, list1, list2);
        //THEN
        assertTrue(resultHoliday.isEmpty());
    }

}