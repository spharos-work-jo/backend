package com.workjo.pointapp.point.common.vo.response;

import com.workjo.pointapp.point.common.domain.PointType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PointEntityRes {

    private Long id;
    private UUID userUuid;
    private int totalPoint;
    private int point;
    private LocalDateTime regDate;
    private PointType type;
    private String title;

}