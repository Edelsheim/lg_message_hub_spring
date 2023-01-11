package com.example.msghub.api.message.dto;

import java.util.ArrayList;
import java.util.List;

public class SmsDto {
    /**
    * @param callback 발신번호
    * @param campaignId 켐페인 ID
    * @param deptCode 부서코드
    * @param msg 메세지내용, 최대 90byte
    * @param recvInfoLst 발송정보 목록, 최대 10건
    * @param fbInfoLst empty list
    */
    public record SmsRequest(
        String callback,
        String campaignId,
        String deptCode,
        String msg,
        List<MessageDto.RecvInfo> recvInfoLst,
        List<MessageDto.FbInfo> fbInfoLst
    ) {
        SmsRequest {
            if (fbInfoLst == null) {
                fbInfoLst = new ArrayList<MessageDto.FbInfo>();
            }
        }
    }
}
