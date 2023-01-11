package com.example.msghub.api.message.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.msghub.common.config.MsgHubConfig;
import com.example.msghub.common.http.WebClientResponse;
import com.example.msghub.common.http.WebClientService;
import com.example.msghub.api.message.dto.KakaoDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KakaoFriendService {
    private final ObjectMapper objectMapper;
    private final MsgHubConfig msgHubConfig;
    private final WebClientService webClientService;

    @Value("${msghub.message.kakaoFriend.path}")
    private String kakaoFriendPath;
    @Value("${msghub.message.kakaoFriend.method}")
    private String kakaoFriendMethod;

    public KakaoFriendService(
        ObjectMapper objectMapper,
        MsgHubConfig msgHubConfig,
        WebClientService webClientService
    ) {
        this.objectMapper = objectMapper;
        this.msgHubConfig = msgHubConfig;
        this.webClientService = webClientService;
    }

    public WebClientResponse<String> send(@NotBlank String token, @NotNull KakaoDto.KakaoFriendRequest requestDto) {
        try {
            final HttpHeaders headers = this.webClientService.makeApplicationJsonBearerHeader(token);
            final String body = this.objectMapper.writeValueAsString(requestDto);
            return this.webClientService.send(this.kakaoFriendMethod, this.msgHubConfig.getBaseUrl(), this.kakaoFriendPath, headers, body);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
            return new WebClientResponse<>(400, null);
        }
    }
}
