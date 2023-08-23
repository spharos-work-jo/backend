package com.workjo.pointapp.point.vo;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@Builder
@NoArgsConstructor
public class PointEarnRequest {
    int paidPrice; // doesn't need if receipt/bill db implemented
    int storeId;
    int receiptId;
    UUID userUuid;
}
