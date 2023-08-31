package com.workjo.pointapp.point.dto.gift;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class PointGiftGetUnrepliedDto {
    private UUID userUuid;

}
