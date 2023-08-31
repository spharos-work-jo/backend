package com.workjo.pointapp.point.vo.response;

import com.workjo.pointapp.point.domain.PointGiftStatus;

import java.util.UUID;

public class PointGiftEntityRes {
    private Long id;
    private String message;
    private PointGiftStatus giftStatus;
    private Long sentPointId;
    private Long receivedPointId;
    private UUID toUserUuid;
    private UUID fromUserUuid;
}
