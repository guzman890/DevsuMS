package com.backend.account.controller;

import com.backend.account.model.dto.HeaderReportDTO;
import com.backend.account.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/reportes")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping
    public Mono<ResponseEntity<HeaderReportDTO>> getReportByDateRangeAndClient(
            @RequestParam("startDate") String startDateInput,
            @RequestParam("endDate") String endDateInput,
            @RequestParam("cliente") String cliente) {
        ZonedDateTime startDate;
        ZonedDateTime endDate;
        try {
            startDateInput += "T00:00:00Z";
            endDateInput += "T00:00:00Z";
            startDate = ZonedDateTime.parse(startDateInput, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            endDate = ZonedDateTime.parse(endDateInput, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        } catch (DateTimeParseException e) {
            return Mono.just(ResponseEntity.badRequest().build());
        }
        return reportService.getReportByDateRangeAndClient(startDate, endDate, Long.valueOf(cliente))
                .map(ResponseEntity::ok);
    }
}
