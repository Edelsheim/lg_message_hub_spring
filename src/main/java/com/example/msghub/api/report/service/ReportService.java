package com.example.msghub.api.report.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.msghub.common.http.WebClientResponse;
import com.example.msghub.api.report.dto.PollingDto;
import com.example.msghub.api.report.dto.SentDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportService {
    private final ObjectMapper objectMapper;
    private final SentService sentService;
    private final PollingService pollingService;

    public WebClientResponse<SentDto.SentResponse> sent(@NotBlank String token, @NotNull SentDto.SentRequest requestDto) {
        WebClientResponse<String> send_response = this.sentService.sent(token, requestDto);
        try {
            SentDto.SentResponse response = null;
            if (send_response.body() != null) {
                response = this.objectMapper.readValue(send_response.body(), SentDto.SentResponse.class);
            }
            return new WebClientResponse<>(send_response.statusCode(), response);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
            return new WebClientResponse<>(400, null);
        }
    }

    public WebClientResponse<PollingDto.PollingResponse> polling(@NotBlank String token) {
        WebClientResponse<String> send_response = this.pollingService.reportPolling(token);
        try {
            PollingDto.PollingResponse response = null;
            if (send_response.body() != null) {
                response = this.objectMapper.readValue(send_response.body(), PollingDto.PollingResponse.class);
            }
            return new WebClientResponse<>(send_response.statusCode(), response);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
            return new WebClientResponse<>(400, null);
        }
    }

    public WebClientResponse<PollingDto.PollingResultResponse> pollingResult(@NotBlank String token, @NotNull PollingDto.PollingResultRequest requestDto) {
        WebClientResponse<String> send_response = this.pollingService.reportPollingResult(token, requestDto);
        try {
            PollingDto.PollingResultResponse response = null;
            if (send_response.body() != null) {
                response = this.objectMapper.readValue(send_response.body(), PollingDto.PollingResultResponse.class);
            }
            return new WebClientResponse<>(send_response.statusCode(), response);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
            return new WebClientResponse<>(400, null);
        }
    }
}
