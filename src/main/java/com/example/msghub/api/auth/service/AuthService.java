package com.example.msghub.api.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.msghub.common.http.WebClientResponse;
import com.example.msghub.api.auth.dto.AuthDto;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final ObjectMapper objectMapper;
    private final AuthTokenService tokenService;
    private final AuthRefreshService refreshService;

    public WebClientResponse<AuthDto.AuthResponse> getAuth(@NotBlank String key) {
        WebClientResponse<String> auth_response = this.tokenService.requestAuth(key);
        try {
            AuthDto.AuthResponse response_record = null;
            if (auth_response.body() != null)
                response_record = this.objectMapper.readValue(auth_response.body(), AuthDto.AuthResponse.class);
            return new WebClientResponse<>(auth_response.statusCode(), response_record);
        } catch (Exception e) {
            return new WebClientResponse<>(400, null);
        }
    }

    public WebClientResponse<AuthDto.RefreshResponse> getRefresh(@NotBlank String refreshToken) {
        WebClientResponse<String> auth_response = this.refreshService.requestRefresh(refreshToken);
        try {
            AuthDto.RefreshResponse response_record = null;
            if (auth_response.body() != null)
                response_record = this.objectMapper.readValue(auth_response.body(), AuthDto.RefreshResponse.class);
            return new WebClientResponse<>(auth_response.statusCode(), response_record);
        } catch (Exception e) {
            return new WebClientResponse<>(400, null);
        }
    }
}
