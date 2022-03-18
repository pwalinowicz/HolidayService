package com.holidayinfo.holidayservice.exception;

public class NotSupportedCountryCodeException extends Throwable {
    public NotSupportedCountryCodeException(String countryCode) {
        super("The following country code in not supported: " + countryCode);
    }
}
