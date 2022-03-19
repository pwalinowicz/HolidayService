package com.holidayinfo.holidayservice.controller;

import com.holidayinfo.holidayservice.exception.NotSupportedCountryCodeException;
import com.holidayinfo.holidayservice.model.RequestHoliday;
import com.holidayinfo.holidayservice.model.ResultHoliday;
import com.holidayinfo.holidayservice.service.HolidayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class HolidayController {
    @Autowired
    private final HolidayService holidayService;

    @PostMapping(path = "/common-holiday",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCommonHoliday(@RequestBody RequestHoliday request) {
        try {
            var result = holidayService.getCommonHoliday(request);
            return result.map(resultHoliday -> ResponseEntity.ok().body(resultHoliday))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        } catch(NotSupportedCountryCodeException | IOException | InterruptedException | URISyntaxException e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(new ResultHoliday());
        }
    }
}
