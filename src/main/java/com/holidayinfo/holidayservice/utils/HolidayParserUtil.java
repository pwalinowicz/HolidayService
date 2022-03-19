package com.holidayinfo.holidayservice.utils;

import com.holidayinfo.holidayservice.model.Holiday;
import com.holidayinfo.holidayservice.model.ResultHoliday;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
@Slf4j
public class HolidayParserUtil {
    public static Optional<LocalDate> getFirstCommonDateInHolidayLists(List<Holiday> holidayList1, List<Holiday> holidayList2) {
        List<LocalDate> commonDatesList = getCommonDatesList(holidayList1, holidayList2);
        if (commonDatesList.isEmpty()) {
            return Optional.empty();
        } else {
            Collections.sort(commonDatesList);
            return Optional.of(commonDatesList.get(0));
        }
    }

    public static List<Holiday> getHolidaysAfterOrEqualDate(LocalDate date, List<Holiday> holidayList) {
        return holidayList.stream()
                .filter(holiday -> holiday.getHolidayDateAsLocalDate().isAfter(date) || holiday.getHolidayDateAsLocalDate().isEqual(date))
                .collect(Collectors.toList());
    }

    public static Optional<ResultHoliday> getOptionalResultHoliday(LocalDate commonDate, List<Holiday> firstCountryCodeHolidays,
                                                                   List<Holiday> secondCountryCodeHolidays) {
        try {
            if (!firstCountryCodeHolidays.isEmpty() && !secondCountryCodeHolidays.isEmpty()) {
                return Optional.of(ResultHoliday.builder()
                        .date(commonDate)
                        .name1(getHolidayLocalNameByDate(firstCountryCodeHolidays, commonDate))
                        .name2(getHolidayLocalNameByDate(secondCountryCodeHolidays, commonDate))
                        .build());
            } else {
                log.info("No common date found");
                return Optional.empty();
            }
        } catch(Exception e){
            log.error("Exception while getting the result holiday. Exception: " + e.getMessage());
            return Optional.empty();
        }
    }

    private static String getHolidayLocalNameByDate(List<Holiday> holidayList, LocalDate localDate) {
        var localNameOptional = holidayList.stream()
                .filter(holiday -> holiday.getHolidayDateAsLocalDate().isEqual(localDate))
                .map(Holiday::getLocalNameOfHoliday)
                .findFirst();
        return localNameOptional.orElse("");
    }

    private static List<LocalDate> getCommonDatesList(List<Holiday> holidayList1, List<Holiday> holidayList2) {
        List<LocalDate> commonDatesList = new ArrayList<>();
        if(holidayList1 != null && holidayList2 != null) {
            for (var holiday : holidayList1) {
                var date = holiday.getHolidayDateAsLocalDate();
                var partialCommonDatesList = holidayList2.stream()
                        .sorted(Comparator.comparing(Holiday::getHolidayDateAsLocalDate))
                        .map(Holiday::getHolidayDateAsLocalDate)
                        .filter(h -> h.isEqual(date))
                        .collect(Collectors.toList());
                if (!partialCommonDatesList.isEmpty()) {
                    commonDatesList.addAll(partialCommonDatesList);
                }
            }
        }
        return commonDatesList;
    }
}
