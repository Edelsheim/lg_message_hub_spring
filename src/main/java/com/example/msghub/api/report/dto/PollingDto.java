package com.example.msghub.api.report.dto;

import java.util.List;

public class PollingDto {
    /**
     * 리포트 조회 요청 response
     * @param code
     * @param message
     * @param data
     */
    public record PollingResponse(
            String code,
            String message,
            PollingResponseData data
    ) {}

    /**
     * 리포트 데이터
     * @param rptKey 리포트 키
     * @param rptCnt 리포트 개수
     * @param rptLst 리포트 목록
     */
    public record PollingResponseData(
            String rptKey,
            Long rptCnt,
            List<ClientKeyDto.ClientKeyResponse> rptLst
    ) {}

    /**
     * 리포트 처리결과 전달 polling
     * @param rptKeyLst 리포트 전달 (polling 방식 ) API 실행 후 리턴받은 rptKey 목록
     */
    public record PollingResultRequest(List<String> rptKeyLst) {}
    public record PollingResultResponse(
            String code,
            String message
    ) {}
}
