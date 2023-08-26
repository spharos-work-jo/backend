package com.workjo.pointapp.point.vo.Request;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class PointEarnReq {
    Long partnerId;
    int paidPrice; // doesn't need if receipt/bill db implemented
    Long storeId;
    Long receiptId;
    String userUuid;
}
