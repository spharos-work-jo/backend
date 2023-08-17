package com.workjo.pointapp.point.vo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointOut {

    private Integer point;
    private Integer totalPoint;
    private String pointType;
    private Boolean used;

}
