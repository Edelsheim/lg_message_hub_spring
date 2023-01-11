package com.example.msghub.api.report.controller;

import com.example.msghub.common.http.WebClientResponse;
import com.example.msghub.common.util.Auth;
import com.example.msghub.api.report.dto.PollingDto;
import com.example.msghub.api.report.dto.SentDto;
import com.example.msghub.api.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/msghub/report")
@Slf4j
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/sent")
    public ResponseEntity<SentDto.SentResponse> sent(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false, defaultValue = "") String authToken,
        @RequestBody SentDto.SentRequest requestDto
    ) {
        if (Auth.validBearer(authToken)) {
            final String token = Auth.getBearerToken(authToken);
            WebClientResponse<SentDto.SentResponse> result = this.reportService.sent(token, requestDto);
            return ResponseEntity.status(result.statusCode())
                    .body(result.body());
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new SentDto.SentResponse(
                            String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                            "Valid Token Fail",
                            null)
                    );
        }
    }

    @GetMapping("/polling")
    public ResponseEntity<PollingDto.PollingResponse> polling(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false, defaultValue = "") String authToken
    ) {
        if (Auth.validBearer(authToken)) {
            final String token = Auth.getBearerToken(authToken);
            WebClientResponse<PollingDto.PollingResponse> result = this.reportService.polling(token);
            return ResponseEntity.status(result.statusCode())
                    .body(result.body());
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new PollingDto.PollingResponse(
                            String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                            "Valid Token Fail",
                            null)
                    );
        }
    }

    @PostMapping("/polling")
    public ResponseEntity<PollingDto.PollingResultResponse> pollingResult(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false, defaultValue = "") String authToken,
        @RequestBody PollingDto.PollingResultRequest requestDto
    ) {
        if (Auth.validBearer(authToken)) {
            final String token = Auth.getBearerToken(authToken);
            WebClientResponse<PollingDto.PollingResultResponse> result = this.reportService.pollingResult(token, requestDto);
            return ResponseEntity.status(result.statusCode())
                    .body(result.body());
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new PollingDto.PollingResultResponse(
                            String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                            "Valid Token Fail")
                    );
        }
    }
}
