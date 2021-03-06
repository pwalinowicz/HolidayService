package com.holidayinfo.holidayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ConfigurationPropertiesScan("com.holidayinfo.holidayservice.configuration")
public class HolidayServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(HolidayServiceApplication.class, args);
	}
}
