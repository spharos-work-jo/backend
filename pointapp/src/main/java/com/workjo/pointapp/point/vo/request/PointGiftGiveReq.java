package com.workjo.pointapp.point.vo.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PointGiftGiveReq {
    private String message;
    private int point;
    //    private String pointPassword;
    private String toUserUuid;
    private String toUserName;
}