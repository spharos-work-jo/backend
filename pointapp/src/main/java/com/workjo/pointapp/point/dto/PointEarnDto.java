package com.workjo.pointapp.point.dto;

import com.workjo.pointapp.point.domain.PointEarn;
import com.workjo.pointapp.point.vo.Request.PointEarnReq;
import com.workjo.pointapp.point.vo.Response.PointEarnRes;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

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
