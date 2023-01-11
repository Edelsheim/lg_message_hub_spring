package com.example.msghub.api.message.dto;

import java.util.List;

public class TotalSendDto {
    /**
    * @param tmpltCode 통합발송 템플릿
    * @param campaignId 캠페인 ID
    * @param deptCode 	부서코드
    * @param recvInfoLst 발송 정보 목록, 최대 10건
    */
    public record TotalSendRequest(
        String tmpltCode,
        String campaignId,
        String deptCode,
        List<MessageDto.SmartRecvInfo> recvInfoLst
    ) {}
}
