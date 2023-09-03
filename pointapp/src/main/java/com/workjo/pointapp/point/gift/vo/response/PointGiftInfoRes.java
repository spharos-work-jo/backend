package com.workjo.pointapp.point.gift.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointGiftInfoRes {
    private Long id;
    private String message;
    private Integer point;
    private Long sentPointId;
    private Long receivedPointId;
    private String toUserUuid;
    private String fromUserUuid;
}
