package com.workjo.pointapp.point.domain;

import com.workjo.pointapp.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PointGiftStatus implements BaseEnum<Character, String> {
    WAIT('W', "WAIT"),
    ACCEPT('A', "ACCEPT"),
    REJECT('R', "REJECT");

    private Character code;
    private String value;


    @Override
    public Character getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
