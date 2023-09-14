package com.workjo.pointapp.point.gift.vo.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class GivePointGiftReq {
    private String message;
    private int point;
    private String pointPassword;
    private String toUserUuid;
    private String toUserName;
}
