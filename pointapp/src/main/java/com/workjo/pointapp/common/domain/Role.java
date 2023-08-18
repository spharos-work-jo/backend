package com.workjo.pointapp.common.domain;

import com.workjo.pointapp.common.AbstractBaseEnumConverter;
import com.workjo.pointapp.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Role implements BaseEnum<Integer, String> {
    USER(0, "USER"),
    TOP_ADMIN(1, "TOP_ADMIN"),
    ADMIN(2, "ADMIN");

    private final Integer code;
    private final String value;


    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractBaseEnumConverter<Role, Integer, String> {
        public Converter() {
            super(Role.class);
        }
    }

    private static final Map<Integer, Role> OPERATOR_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Role::getCode, Function.identity())));

    public static Role find(Integer code) {
        if(code == null){
            return USER;
        } else if (OPERATOR_MAP.containsKey(code)) {
            return OPERATOR_MAP.get(code);
        }
        throw new IllegalArgumentException("해당 enum 없음");
    }

}