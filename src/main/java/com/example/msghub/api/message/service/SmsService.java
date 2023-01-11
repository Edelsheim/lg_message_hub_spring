package com.example.msghub.api.message.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.msghub.common.config.MsgHubConfig;
import com.example.msghub.common.http.WebClientResponse;
import com.example.msghub.common.http.WebClientService;
import com.example.msghub.common.util.ValidResult;
import com.example.msghub.api.message.dto.MessageDto;
import com.example.msghub.api.message.dto.SmsDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SmsService {
    private final ObjectMapper objectMapper;
    private final MsgHubConfig msgHubConfig;
    private final WebClientService webClientService;

    @Value("${msghub.message.sms.path}")
    private String smsPath;
    @Value("${msghub.message.sms.method}")
    private String smsMethod;

    public SmsService(
        ObjectMapper objectMapper,
        MsgHubConfig msgHubConfig,
        WebClientService webClientService
    ) {
        this.objectMapper = objectMapper;
        this.msgHubConfig = msgHubConfig;
        this.webClientService = webClientService;
    }

    public WebClientResponse<String> send(@NotBlank String token, @NotNull SmsDto.SmsRequest requestDto) {
        try {
            final ValidResult valid_result = this.validSmsRequest(requestDto);
            if (valid_result.valid()) {
                final HttpHeaders headers = this.webClientService.makeApplicationJsonBearerHeader(token);
                final String body = this.objectMapper.writeValueAsString(requestDto);
                return this.webClientService.send(this.smsMethod, this.msgHubConfig.getBaseUrl(), this.smsPath, headers, body);
            }
            else {
                log.error(valid_result.message());
                System.out.println(valid_result.message());
                MessageDto.MessageResponse error_response = new MessageDto.MessageResponse("0", valid_result.message(), null);
                return new WebClientResponse<>(400, this.objectMapper.writeValueAsString(error_response));
            }
        }
        catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
            return new WebClientResponse<>(400, null);
        }
    }

    /**
     * This is valid SmsRequest
     * @return isBlank -> true, other -> fail(error)
     */
    private ValidResult validSmsRequest(SmsDto.SmsRequest requestData) {
        if (requestData == null)
            return new ValidResult(false, "데이터 확인 요청");
        else if (requestData.msg() == null || requestData.msg().isBlank())
            return new ValidResult(false, "메세지 내용 확인 요청");
        else if (requestData.msg().getBytes(StandardCharsets.UTF_8).length > 90)
            return new ValidResult(false, "메세지 내용 90바이트 초과");
        else if (requestData.recvInfoLst() == null || requestData.recvInfoLst().isEmpty())
            return new ValidResult(false, "발송 정보 확인 요청");
        else if (requestData.recvInfoLst().size() > 10)
            return new ValidResult(false, "발송 정보 최대 10건");

        return new ValidResult(true, "");
    }
}
