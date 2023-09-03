package com.workjo.pointapp.point.gift.vo.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyPointGiftReq {
    private Long id;
    private String message;
    private Integer point;
    private Long sentPointId;
    private Long receivedPointId;
    private String toUserUuid;
    private String fromUserUuid;
}
