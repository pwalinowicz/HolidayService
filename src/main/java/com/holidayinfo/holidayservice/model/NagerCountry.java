package com.holidayinfo.holidayservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NagerCountry implements Country{
    private String countryCode;
    private String name;
}



