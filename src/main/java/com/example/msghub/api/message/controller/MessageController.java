package com.example.msghub.api.message.controller;

import com.example.msghub.common.http.WebClientResponse;
import com.example.msghub.common.util.Auth;
import com.example.msghub.api.message.dto.*;
import com.example.msghub.api.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/msghub/message")
@Slf4j
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/sms")
    public ResponseEntity<MessageDto.MessageResponse> sendSms(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false, defaultValue = "") String authToken,
        @RequestBody SmsDto.SmsRequest messageDto
    ) {
        if (Auth.validBearer(authToken)) {
            final String token = Auth.getBearerToken(authToken);
            WebClientResponse<MessageDto.MessageResponse> result = this.messageService.sendSms(token, messageDto);
            return ResponseEntity.status(result.statusCode())
                    .body(result.body());
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageDto.MessageResponse(
                            String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                            "Valid Token Fail",
                            null)
                    );
        }
    }

    @PostMapping("/mms")
    public ResponseEntity<MessageDto.MessageResponse> sendMms(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false, defaultValue = "") String authToken,
        @RequestBody MmsDto.MmsRequest messageDto
    ) {
        if (Auth.validBearer(authToken)) {
            String token = Auth.getBearerToken(authToken);
            WebClientResponse<MessageDto.MessageResponse> result = this.messageService.sendMms(token, messageDto);
            return ResponseEntity.status(result.statusCode())
                    .body(result.body());
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageDto.MessageResponse(
                            String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                            "Valid Token Fail",
                            null)
                    );
        }
    }

    @PostMapping("/mms/file")
    public ResponseEntity<MessageDto.MessageResponse> sendMmsFile(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false, defaultValue = "") String authToken,
        @RequestBody MmsDto.MmsFileRequest messageDto
    ) {
        if (Auth.validBearer(authToken)) {
            String token = Auth.getBearerToken(authToken);
            WebClientResponse<MessageDto.MessageResponse> result = this.messageService.sendMmsFile(token, messageDto);
            return ResponseEntity.status(result.statusCode())
                    .body(result.body());
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageDto.MessageResponse(
                            String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                            "Valid Token Fail",
                            null)
                    );
        }
    }

    @PostMapping("/rcs")
    public ResponseEntity<MessageDto.MessageResponse> sendRcs(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false, defaultValue = "") String authToken,
        @RequestBody RcsDto.RcsRequest messageDto
    ) {
        if (Auth.validBearer(authToken)) {
            String token = Auth.getBearerToken(authToken);
            WebClientResponse<MessageDto.MessageResponse> result = this.messageService.sendRcs(token, messageDto);
            return ResponseEntity.status(result.statusCode())
                    .body(result.body());
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageDto.MessageResponse(
                            String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                            "Valid Token Fail",
                            null)
                    );
        }
    }

    @PostMapping("/alimtalk")
    public ResponseEntity<MessageDto.MessageResponse> sendAlimTalk(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false, defaultValue = "") String authToken,
        @RequestBody KakaoDto.KakaoAlimRequest messageDto
    ) {
        if (Auth.validBearer(authToken)) {
            String token = Auth.getBearerToken(authToken);
            WebClientResponse<MessageDto.MessageResponse> result = this.messageService.sendKakaoAlimTalk(token, messageDto);
            return ResponseEntity.status(result.statusCode())
                    .body(result.body());
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageDto.MessageResponse(
                            String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                            "Valid Token Fail",
                            null)
                    );
        }
    }

    @PostMapping("/friendtalk")
    public ResponseEntity<MessageDto.MessageResponse> sendFriendTalk(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false, defaultValue = "") String authToken,
        @RequestBody KakaoDto.KakaoFriendRequest messageDto
    ) {
        if (Auth.validBearer(authToken)) {
            String token = Auth.getBearerToken(authToken);
            WebClientResponse<MessageDto.MessageResponse> result = this.messageService.sendKakaoFriendTalk(token, messageDto);
            return ResponseEntity.status(result.statusCode())
                    .body(result.body());
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageDto.MessageResponse(
                            String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                            "Valid Token Fail",
                            null)
                    );
        }
    }

    @PostMapping("/push")
    public ResponseEntity<MessageDto.MessageResponse> sendPush(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false, defaultValue = "") String authToken,
        @RequestBody PushDto.PushRequest messageDto
    ) {
        if (Auth.validBearer(authToken)) {
            String token = Auth.getBearerToken(authToken);
            WebClientResponse<MessageDto.MessageResponse> result = this.messageService.sendPush(token, messageDto);
            return ResponseEntity.status(result.statusCode())
                    .body(result.body());
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageDto.MessageResponse(
                            String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                            "Valid Token Fail",
                            null)
                    );
        }
    }

    @PostMapping("/send")
    public ResponseEntity<MessageDto.MessageResponse> sendTotal(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false, defaultValue = "") String authToken,
        @RequestBody TotalSendDto.TotalSendRequest messageDto
    ) {
        if (Auth.validBearer(authToken)) {
            String token = Auth.getBearerToken(authToken);
            WebClientResponse<MessageDto.MessageResponse> result = this.messageService.sendTotal(token, messageDto);
            return ResponseEntity.status(result.statusCode())
                    .body(result.body());
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageDto.MessageResponse(
                            String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                            "Valid Token Fail",
                            null)
                    );
        }
    }
}
