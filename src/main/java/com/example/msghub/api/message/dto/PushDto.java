package com.example.msghub.api.message.dto;

import java.util.HashMap;
import java.util.List;

public class PushDto {
    /**
    * @param callback 발신번호
    * @param campaignId 캠페인 ID
    * @param deptCode 부서코드
    * @param msg 메시지 내용, 최대 2000자
    * @param appId 앱 아이디
    * @param ext 확장 커스텀 메시지, 최대 1000 Byte 이내
    * @param fileId 파일 아이디
    * @param recvInfoLst 발송 정보 목록(최대 10건 발송가능)
    * @param fbInfoLst fallback 정보 목록
    */
    public record PushRequest(
        String callback,
        String campaignId,
        String deptCode,
        String msg,
        String appId,
        HashMap<String, String> ext,
        String fileId,
        List<MessageDto.RecvInfo> recvInfoLst,
        List<MessageDto.FbInfo> fbInfoLst
    ) {}
}
