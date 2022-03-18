package com.holidayinfo.holidayservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultHoliday {
    private LocalDate date;
    private String name1;
    private String name2;
}
