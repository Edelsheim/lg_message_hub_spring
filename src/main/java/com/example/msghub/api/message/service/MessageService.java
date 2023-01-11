package com.example.msghub.api.msghub.message.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.msghub.api.common.http.WebClientResponse;
import com.example.msghub.api.msghub.message.dto.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {
    private final ObjectMapper objectMapper;
    private final SmsService smsService;
    private final MmsService mmsService;
    private final RcsService rcsService;
    private final KakaoAlimService alimService;
    private final KakaoFriendService friendService;
    private final PushService pushService;
    private final TotalSendService totalSendService;

    public WebClientResponse<MessageDto.MessageResponse> sendSms(@NotBlank String token, @NotNull SmsDto.SmsRequest requestDto) {
        WebClientResponse<String> send_response = this.smsService.send(token, requestDto);
        try {
            MessageDto.MessageResponse response = null;
            if (send_response.body() != null) {
                response = this.objectMapper.readValue(send_response.body(), MessageDto.MessageResponse.class);
            }

            return new WebClientResponse<>(send_response.statusCode(), response);
        }
        catch (Exception e) {
            return new WebClientResponse<>(400, null);
        }
    }

    public WebClientResponse<MessageDto.MessageResponse> sendMms(@NotBlank String token, @NotNull MmsDto.MmsRequest requestDto) {
        WebClientResponse<String> send_response = this.mmsService.send(token, requestDto);
        try {
            MessageDto.MessageResponse response = null;
            if (send_response.body() != null) {
                response = this.objectMapper.readValue(send_response.body(), MessageDto.MessageResponse.class);
            }

            return new WebClientResponse<>(send_response.statusCode(), response);
        }
        catch (Exception e) {
            return new WebClientResponse<>(400, null);
        }
    }

    public WebClientResponse<MessageDto.MessageResponse> sendMmsFile(@NotBlank String token, @NotNull MmsDto.MmsFileRequest requestDto) {
        WebClientResponse<String> send_response = this.mmsService.sendFile(token, requestDto);
        try {
            MessageDto.MessageResponse response = null;
            if (send_response.body() != null) {
                response = this.objectMapper.readValue(send_response.body(), MessageDto.MessageResponse.class);
            }

            return new WebClientResponse<>(send_response.statusCode(), response);
        }
        catch (Exception e) {
            return new WebClientResponse<>(400, null);
        }
    }

    public WebClientResponse<MessageDto.MessageResponse> sendRcs(@NotBlank String token, @NotNull RcsDto.RcsRequest requestDto) {
        WebClientResponse<String> send_response = this.rcsService.send(token, requestDto);
        try {
            MessageDto.MessageResponse response = null;
            if (send_response.body() != null) {
                response = this.objectMapper.readValue(send_response.body(), MessageDto.MessageResponse.class);
            }

            return new WebClientResponse<>(send_response.statusCode(), response);
        }
        catch (Exception e) {
            return new WebClientResponse<>(400, null);
        }
    }

    public WebClientResponse<MessageDto.MessageResponse> sendKakaoAlimTalk(@NotBlank String token, @NotNull KakaoDto.KakaoAlimRequest requestDto) {
        WebClientResponse<String> send_response = this.alimService.send(token, requestDto);
        try {
            MessageDto.MessageResponse response = null;
            if (send_response.body() != null) {
                response = this.objectMapper.readValue(send_response.body(), MessageDto.MessageResponse.class);
            }

            return new WebClientResponse<>(send_response.statusCode(), response);
        }
        catch (Exception e) {
            return new WebClientResponse<>(400, null);
        }
    }

    public WebClientResponse<MessageDto.MessageResponse> sendKakaoFriendTalk(@NotBlank String token, @NotNull KakaoDto.KakaoFriendRequest requestDto) {
        WebClientResponse<String> send_response = this.friendService.send(token, requestDto);
        try {
            MessageDto.MessageResponse response = null;
            if (send_response.body() != null) {
                response = this.objectMapper.readValue(send_response.body(), MessageDto.MessageResponse.class);
            }

            return new WebClientResponse<>(send_response.statusCode(), response);
        }
        catch (Exception e) {
            return new WebClientResponse<>(400, null);
        }
    }

    public WebClientResponse<MessageDto.MessageResponse> sendPush(@NotBlank String token, @NotNull PushDto.PushRequest requestDto) {
        WebClientResponse<String> send_response = this.pushService.send(token, requestDto);
        try {
            MessageDto.MessageResponse response = null;
            if (send_response.body() != null) {
                response = this.objectMapper.readValue(send_response.body(), MessageDto.MessageResponse.class);
            }

            return new WebClientResponse<>(send_response.statusCode(), response);
        }
        catch (Exception e) {
            return new WebClientResponse<>(400, null);
        }
    }

    public WebClientResponse<MessageDto.MessageResponse> sendTotal(@NotBlank String token, @NotNull TotalSendDto.TotalSendRequest requestDto) {
        WebClientResponse<String> send_response = this.totalSendService.send(token, requestDto);
        try {
            MessageDto.MessageResponse response = null;
            if (send_response.body() != null) {
                response = this.objectMapper.readValue(send_response.body(), MessageDto.MessageResponse.class);
            }

            return new WebClientResponse<>(send_response.statusCode(), response);
        }
        catch (Exception e) {
            return new WebClientResponse<>(400, null);
        }
    }
}
