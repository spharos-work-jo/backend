package com.workjo.pointapp.alarm.domain;


import com.workjo.pointapp.common.AbstractBaseEnumConverter;
import com.workjo.pointapp.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum AlarmType implements BaseEnum<String, String> {
	EVENT("E", "이벤트"),
	GIFT("G", "선물"),
	COUPON("C", "쿠폰"),
	ETC("T", "기타");
	private final String code;
	private final String value;

	@jakarta.persistence.Converter(autoApply = true)
	static class Converter extends AbstractBaseEnumConverter<AlarmType, String, String> {

		public Converter() {
			super(AlarmType.class);
		}

	}
}
