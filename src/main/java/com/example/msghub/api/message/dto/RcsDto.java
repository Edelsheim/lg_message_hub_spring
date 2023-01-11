package com.example.msghub.api.message.dto;

import java.util.List;

public class RcsDto {
    /**
    * @param messagebaseId 메시지베이스 ID (^[a-zA-Z0-9-_]{0,20}$)
    * @param callback 발신번호
    * @param header 0=정보성 메시지, 1=광고성 메시지
    * @param footer 무료수신거부 번호 (*header의 값이 광고성일 때 footer 값을 포함하지 않고 발송하면 실패 처리)
    * @param copyAllowed 사용자의 복사/공유 허용여부
    * @param expiryOption expire 옵션(1:24시간, 2:30초, 3:3분, 4:1시간)
    * @param agencyId 	대행사 아이디
    * @param campaignId 캠페인 ID
    * @param deptCode 부서코드
    * @param buttons 버튼 Object
    * @param recvInfoLst 발송 정보 목록(최대 10건 발송가능)
    * @param fbInfoLst fallback 정보 목록
    */
    public record RcsRequest(
        String messagebaseId,
        String callback,
        String header,
        String footer,
        Boolean copyAllowed,
        String expiryOption,
        String agencyId,
        String campaignId,
        String deptCode,
        Object buttons,
        List<MessageDto.RecvInfo> recvInfoLst,
        List<MessageDto.FbInfo> fbInfoLst
    ) {}
}
