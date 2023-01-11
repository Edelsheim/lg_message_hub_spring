package com.example.msghub.api.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.msghub.common.config.MsgHubConfig;
import com.example.msghub.common.http.WebClientResponse;
import com.example.msghub.common.http.WebClientService;
import com.example.msghub.api.auth.dto.AuthDto;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthRefreshService {
    private final ObjectMapper objectMapper;
    private final MsgHubConfig msgHubConfig;
    private final WebClientService webClientService;

    @Value("${msghub.auth.refreshToken.path}")
    private String refreshPath;
    @Value("${msghub.auth.refreshToken.method}")
    private String refreshMethod;

    public AuthRefreshService(ObjectMapper objectMapper, MsgHubConfig msgHubConfig, WebClientService webClientService) {
        this.objectMapper = objectMapper;
        this.MsgHubConfig = msgHubConfig;
        this.webClientService = webClientService;
    }


    public WebClientResponse<String> requestRefresh(@NotBlank String token) {
        try {
            final HttpHeaders headers = this.webClientService.makeApplicationJsonBearerHeader(token);

            final AuthDto.RefreshRequest request = new AuthDto.RefreshRequest();
            final String body = this.objectMapper.writeValueAsString(request);

            return this.webClientService.send(this.refreshMethod, this.msgHubConfig.getBaseUrl(), this.refreshPath, headers, body);
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
            return new WebClientResponse<>(400, null);
        }
    }
}
