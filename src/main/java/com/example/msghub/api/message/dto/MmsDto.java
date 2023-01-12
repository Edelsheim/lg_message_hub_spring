package com.example.msghub.api.message.dto;

import java.util.ArrayList;
import java.util.List;

public class MmsDto {
    /**
    * @param callback 발신번호
    * @param campaignId 켐페인 ID
    * @param deptCode 부서코드
    * @param title 제목, 최대 40byte
    * @param msg 메세지내용, 최대 2000byte
    * @param fileIdLst file id 목록
    * @param recvInfoLst 발송정보 목록, 최대 10건
    * @param fbInfoLst empty list
    */
    public record MmsRequest(
        String callback,
        String campaignId,
        String deptCode,
        String title,
        String msg,
        List<String> fileIdLst,
        List<MessageDto.RecvInfo> recvInfoLst,
        List<MessageDto.FbInfo> fbInfoLst
    ) {
        public MmsRequest {
            if (fileIdLst == null) {
                fileIdLst = new ArrayList<String>();
            }
            if (fbInfoLst == null) {
                fbInfoLst = new ArrayList<MessageDto.FbInfo>();
            }
        }
    }

    public record MmsFileRequest(
        MessageDto.ReqMsg reqMsg
    ) {}
}
