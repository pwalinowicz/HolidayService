package com.holidayinfo.holidayservice.model;

import java.time.LocalDate;

public interface Holiday {
    LocalDate getHolidayDateAsLocalDate();
    String getLocalNameOfHoliday();
}
