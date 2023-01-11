package com.example.msghub.api.message.dto;

import java.util.List;

public class KakaoDto {
    /**
    * @param callback 발신번호
    * @param campaignId 캠페인 ID
    * @param deptCode 부서코드
    * @param title 알림톡 강조표기 제목, 최대 50 Byte (* 템플릿 강조유형 중 강조표기형 일때만 사용)
    * @param msg 메시지 내용, 최대 1000자
    * @param senderKey 카카오톡 발신 프로필키
    * @param tmpltKey 템플릿 키
    * @param buttons 버튼 목록
    * @param recvInfoLst 발송 정보 목록(최대 10건 발송가능)
    * @param fbInfoLst fallback 정보 목록
    */
    public record KakaoAlimRequest(
        String callback,
        String campaignId,
        String deptCode,
        String title,
        String msg,
        String senderKey,
        String tmpltKey,
        List<LMKakaoButton> buttons,
        List<MessageDto.RecvInfo> recvInfoLst,
        List<MessageDto.FbInfo> fbInfoLst
    ) {}


    /**
    * @param callback 발신번호
    * @param campaignId 캠페인 ID
    * @param deptCode 부서코드
    * @param wideImageYn 와이드 이미지 여부(Y/N)
    * @param fileId 파일 아이디
    * @param adFlag 광고 표기 여부(Y/N)
    * @param msg 메시지 내용
    * @param image 이미지
    * @param senderKey 카카오톡 발신 프로필키
    * @param buttons 버튼 목록
    * @param recvInfoLst 발송 정보 목록(최대 10건 발송가능)
    * @param fbInfoLst fallback 정보 목록
    */
    public record KakaoFriendRequest(
        String callback,
        String campaignId,
        String deptCode,
        String wideImageYn,
        String fileId,
        String adFlag,
        String msg,
        String image,
        String senderKey,
        List<LMKakaoButton> buttons,
        List<MessageDto.RecvInfo> recvInfoLst,
        List<MessageDto.FbInfo> fbInfoLst
    ) {}


    /**
     * @param name 버튼이름
     * @param linkType 버튼타입
     * @param linkMo mobile 환경에서 버튼 클릭 시 이동할 url, 최대 255 Byte
     * @param linkPc pc 환경에서 버튼 클릭 시 이동할 url, 최대 255 Byte
     * @param linkAnd mobile android 환경에서 버튼 클릭 시 실행할 application custom scheme
     * @param linkIos mobile ios 환경에서 버튼 클릭 시 실행할 application custom scheme
     */
    public record LMKakaoButton(
        String name,
        String linkType,
        String linkMo,
        String linkPc,
        String linkAnd,
        String linkIos
    ) {}

    /*
    버튼 타입
    WL : linkMo 필수, linkPc 옵션
    AL : linkIos, linkAnd, linkMo, linkPc 중 2가지 필수 입력
    BK : 해당 버튼 텍스트 전송
    MD : 해당 버튼 텍스트 + 메시지 본문 전송
    BC : 상담톡 전환
    BT : 봇 전환
    DS : 메시지 내 송장번호 이용한 배송조회페이지로 연결 (quickReplies사용불가)
    AC : 채널추가 -광고추가형, 복합형템플릿에서만 사용가능 -버튼단톡 또는 최상단(첫번째버튼)에만 추가가능 (quickReplies사용불가)
    */
}
