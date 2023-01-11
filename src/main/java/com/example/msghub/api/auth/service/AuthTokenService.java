package com.example.msghub.api.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.msghub.common.config.MsgHubConfig;
import com.example.msghub.common.http.WebClientService;
import com.example.msghub.common.http.WebClientResponse;
import com.example.msghub.common.util.Encrypt;
import com.example.msghub.api.auth.dto.AuthDto;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthTokenService {
    private final ObjectMapper objectMapper;
    private final MsgHubConfig msgHubConfig;
    private final WebClientService webClientService;

    @Value("${msghub.auth.accessToken.path}")
    private String accessTokenPath;
    @Value("${msghub.auth.accessToken.method}")
    private String accessTokenMethod;

    public AuthTokenService(ObjectMapper objectMapper, MsgHubConfig msgHubConfig, WebClientService webClientService) {
        this.objectMapper = objectMapper;
        this.msgHubConfig = msgHubConfig;
        this.webClientService = webClientService;
    }

    public WebClientResponse<String> requestAuth(@NotBlank String key) {
        try {
            final HttpHeaders headers = this.webClientService.makeApplicationJsonBearerHeader(null);

            final String enc1 = Encrypt.SHA512ToString(this.msgHubConfig.getRawPwd());
            final String enc2 = enc1 + "." + key;
            final String pwd = Encrypt.SHA512ToString(enc2);
            final AuthDto.AuthRequest req = new AuthDto.AuthRequest(this.msgHubConfig.getApiKey(), pwd);
            final String body = this.objectMapper.writeValueAsString(req);
            final String uri = this.accessTokenPath + "/" + key;
            return this.webClientService.send(this.accessTokenMethod, this.msgHubConfig.getBaseUrl(), uri, headers, body);
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
            return new WebClientResponse<>(400, null);
        }
    }
}
