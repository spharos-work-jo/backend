package com.workjo.pointapp.point.earn.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class EarnPointDto {
    Long receiptId;
    int paidPrice;
    Long storeId;
    Long pointId;
    Long partnerId;
    UUID userUuid;
    Boolean isSucceeded = null;

}
