package com.workjo.pointapp.point.earn.vo;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointEarnReq {
    Long partnerId;
    int paidPrice; // doesn't need if receipt/bill db implemented
    Long storeId;
    Long receiptId;
    String userUuid;
}
