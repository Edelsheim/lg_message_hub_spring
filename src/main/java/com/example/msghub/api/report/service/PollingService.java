package com.example.msghub.api.report.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.msghub.common.config.MsgHubConfig;
import com.example.msghub.common.http.WebClientResponse;
import com.example.msghub.common.http.WebClientService;
import com.example.msghub.api.report.dto.PollingDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PollingService {
    private final ObjectMapper objectMapper;
    private final MsgHubConfig msgHubConfig;
    private final WebClientService webClientService;

    @Value("${msghub.message.reportPolling.path}")
    private String pollingPath;
    @Value("${msghub.message.reportPolling.method}")
    private String pollingMethod;

    @Value("${msghub.message.reportPollingResult.path}")
    private String pollingResultPath;
    @Value("${msghub.message.reportPollingResult.method}")
    private String pollingResultMethod;

    public PollingService(
        ObjectMapper objectMapper,
        MsgHubConfig msgHubConfig,
        WebClientService webClientService
    ) {
        this.objectMapper = objectMapper;
        this.msgHubConfig = msgHubConfig;
        this.webClientService = webClientService;
    }

    public WebClientResponse<String> reportPolling(@NotBlank String token) {
        try {
            final HttpHeaders headers = this.webClientService.makeApplicationJsonBearerHeader(token);
            return this.webClientService.send(this.pollingMethod, this.msgHubConfig.getBaseUrl(), this.pollingPath, headers, null);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
            return new WebClientResponse<>(400, null);
        }
    }

    public WebClientResponse<String> reportPollingResult(@NotBlank String token, @NotNull PollingDto.PollingResultRequest requestDto) {
        try {
            final HttpHeaders headers = this.webClientService.makeApplicationJsonBearerHeader(token);
            final String body = this.objectMapper.writeValueAsString(requestDto);
            return this.webClientService.send(this.pollingResultMethod, this.lgMessageHubConfig.getBaseUrl(), this.pollingResultPath, headers, body);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
            return new WebClientResponse<>(400, null);
        }
    }
}
