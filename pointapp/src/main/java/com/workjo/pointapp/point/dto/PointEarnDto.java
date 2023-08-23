package com.workjo.pointapp.point.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Data
public class PointEarnDto {
    int paidPrice; // doesn't need if receipt/bill db implemented
    Long storeId;
    Long partnerId;
    Long receiptId;
    UUID userUuid;
}
