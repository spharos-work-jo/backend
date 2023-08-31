package com.workjo.pointapp.point.dto.earn;

import lombok.Data;

import java.util.UUID;

@Data
public class PointEarnDto {
    Long receiptId;
    int paidPrice; //todo doesn't need if receipt/bill db implemented
    Long storeId;
    Long pointId;
    Long partnerId;
    UUID userUuid;
    boolean receiptDisplayable;
    Boolean isSucceeded = null;

}
