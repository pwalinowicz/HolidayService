package com.holidayinfo.holidayservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestHoliday {
    private LocalDate date;
    private String countryCode1;
    private String countryCode2;
}
