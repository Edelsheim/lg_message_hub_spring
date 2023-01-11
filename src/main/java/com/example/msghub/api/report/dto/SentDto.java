package com.example.msghub.api.report.dto;

import java.util.List;

public class SentDto {

    /**
     * 리포트 조회 request
     * @param cliKeyLst 메시지키 목록(최대 10건)
     */
    public record SentRequest(
            List<ClientKeyDto.ClientKeyRequest> cliKeyLst
    ) {}

    /**
     * 리포트 조회 response
     * @param code  리포트 확인 요청에 대한 결과 응답코드
     * @param message   리포트 확인 요청에 대한 결과 응답코드 설명
     * @param data 결과 데이터
     */
    public record SentResponse(
            String code,
            String message,
            ClientKeyDto.ClientKeyResponseList data
    ) {}
}
