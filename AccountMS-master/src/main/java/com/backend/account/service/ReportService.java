package com.backend.account.service;

import com.backend.account.model.dto.HeaderReportDTO;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

public interface ReportService {
    Mono<HeaderReportDTO> getReportByDateRangeAndClient(ZonedDateTime startDate, ZonedDateTime endDate, Long clientId);
}
