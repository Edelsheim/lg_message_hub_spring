package com.example.msghub.api.message.dto;

import java.util.HashMap;
import java.util.List;

public class MessageDto {
    /**
    * 메세지 공용 response
    * @param code 메시지 발송 요청에 대한 결과 응답코드
    * @param message 메시지 발송 요청에 대한 결과 코드 설명
    * @param data 메시지 발송 요청에 대한 결과 데이터 목록
    */
    public record MessageResponse(
        String code,
        String message,
        List<ResponseData> data
    ) {}

    public record ReqMsg(
        String callback,
        String campaignId,
        String deptCode,
        String title,
        List<RecvInfo> recvInfoLst,
        List<FbInfo> fbInfoLst
    ) {}

    /**
     * @param cliKey 클라이언트키: 고객사에서 부여하는 메시지 고유 키(^[a-zA-Z0-9-_.@]{1,30}$)
     * @param phone 수신번호(^[0-9-]{1,20}$)
     * @param mergeData 가변데이터
     */
    public record RecvInfo(
        String cliKey,
        String phone,
        HashMap<String, String> mergeData
    ) {}

    /**
     * @param cliKey 클라이언트키: 고객사에서 부여하는 메시지 고유 키(^[a-zA-Z0-9-_.@]{1,30}$)
     * @param phone 수신번호(^[0-9-]{1,20}$)
     * @param callback 발신번호
     * @param cuid 앱 로그인 시 사용되는 아이디
     * @param kvData 가변데이터
     * @param fileData 파일데이터 없을 시 템플릿에 등록된 파일 기본 전송
     */
    public record SmartRecvInfo(
        String cliKey,
        String phone,
        String callback,
        String cuid,
        HashMap<String, String> kvData,
        HashMap<String, String> fileData
    ) {}

    /**
     * @param ch 채널(SMS/MMS)
     * @param title 제목, 최대 40Byte
     * @param msg 메시지
     * @param fileId 파일 아이디
     */
    public record FbInfo(
        String ch,
        String title,
        String msg,
        String fileId
    ) {}

    /**
     * @param cliKey 클라이언트키: 고객사에서 부여하는 메시지 고유 키
     * @param msgKey U+ 메시지 허브 시스템에서 부여한 메시지 고유 키
     * @param phone 수신번호
     * @param code 메시지 발송 결과 코드
     * @param message 메시지 발송 결과 코드 설명
     */
    public record ResponseData(
        String cliKey,
        String msgKey,
        String phone,
        String code,
        String message
    ) {}
}
