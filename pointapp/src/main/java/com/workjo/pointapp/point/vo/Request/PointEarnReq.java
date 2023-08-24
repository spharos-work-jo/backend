package com.workjo.pointapp.point.vo.Request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointEarnReq {
    int paidPrice; // doesn't need if receipt/bill db implemented
    Long storeId;
    Long partnerId;
    Long receiptId;
    UUID userUuid;
}
