package com.example.msghub.api.report.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.msghub.config.MsgHubConfig;
import com.example.msghub.http.WebClientResponse;
import com.example.msghub.http.WebClientService;
import com.example.msghub.api.report.dto.SentDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SentService {
    private final ObjectMapper objectMapper;
    private final MsgHubConfig msgHubConfig;
    private final WebClientService webClientService;

    @Value("${msghub.message.report.path}")
    private String sentPath;
    @Value("${msghub.message.report.method}")
    private String sentMethod;

    public SentService(ObjectMapper objectMapper, MsgHubConfig msgHubConfig, WebClientService webClientService) {
        this.objectMapper = objectMapper;
        this.msgHubConfig = msgHubConfig;
        this.webClientService = webClientService;
    }

    /**
     * 리포트 조회
     * @param token header bearer token
     * @param requestDto request dto
     * @return WebClientResponse<String>
     */
    public WebClientResponse<String> sent(@NotBlank String token, @NotNull SentDto.SentRequest requestDto) {
        try {
            final HttpHeaders headers = this.webClientService.makeApplicationJsonBearerHeader(token);
            final String body = this.objectMapper.writeValueAsString(requestDto);
            return this.webClientService.send(this.sentMethod, this.msgHubConfig.getBaseUrl(), this.sentPath, headers, body);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
            return new WebClientResponse<>(400, null);
        }
    }
}
