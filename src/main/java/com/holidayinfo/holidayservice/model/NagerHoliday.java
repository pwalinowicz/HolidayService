package com.holidayinfo.holidayservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NagerHoliday implements Holiday{
    private LocalDate date;
    private String localName;
    private String name;
    private String countryCode;
    private boolean fixed;
    private boolean global;
    private int launchYear;
    private String[] types;

    @Override
    public LocalDate getHolidayDateAsLocalDate() {
        return this.date;
    }

    @Override
    public String getLocalNameOfHoliday() {
        return this.localName;
    }
}
