package com.example.msghub.api.report.dto;

import java.util.List;

public class ClientKeyDto {
    /**
     * 리포트 조회 request body
     * @param cliKey 메시지키
     * @param reqDt cliKey에 해당하는 요청일자
     */
    public record ClientKeyRequest(
        String cliKey,
        String reqDt
    ) {}

    /**
     * 리포트 조회 response body
     * @param cliKeyLst 메시지키 목록
     */
    public record ClientKeyResponseList(List<ClientKeyResponse> cliKeyLst) {}

    /**
     * cliKeyLst data
     * @param msgKey U+ 메시지 허브 시스템에서 부여한 메시지 고유 키
     * @param cliKey 	클라이언트키: 고객사에서 부여하는 메시지 고유 키
     * @param status 메시지 상태
     * @param ch 채널
     * @param resultCode 메시지 발송 결과코드
     * @param resultCodeDesc 메시지 발송 결과코드 설명
     * @param fbReasonLst Fallback 사유 List
     * @param telco 이통사(*xMS, RCS만 해당)
     * @param rptDt 	결과 수신 일시
     * @param productCode 	상품코드
     */
    public record ClientKeyResponse(
        String msgKey,
        String cliKey,
        String status,
        String ch,
        String resultCode,
        String resultCodeDesc,
        List<FbReason> fbReasonLst,
        String telco,
        String rptDt,
        String productCode
    ) {}

    /**
     * FbReason Fallback 사유
     * @param ch 	채널
     * @param fbResultCode fb 결과코드
     * @param fbResultDesc 	fb 결과설명
     * @param telco 	이통사(*xMS, RCS만 해당)
     */
    public record FbReason(
        String ch,
        String fbResultCode,
        String fbResultDesc,
        String telco
    ) {}
}