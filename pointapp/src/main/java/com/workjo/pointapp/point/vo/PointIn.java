package com.workjo.pointapp.point.vo;

import lombok.Data;

@Data
public class PointIn {

    private Integer point;
    private String pointType;
    private Boolean used;
    private String loginId;

}
