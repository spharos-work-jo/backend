package com.workjo.pointapp.point.earn.dto;

import com.workjo.pointapp.common.domain.BaseRegDateTime;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EarnPointDto {
    private Long receiptId;
    private Long storeId;
    private Long pointId;
    private Long partnerId;
    private LocalDateTime earnedDate;

    private UUID userUuid;
    private int paidPrice;


    private Boolean isSucceeded = null;

}
