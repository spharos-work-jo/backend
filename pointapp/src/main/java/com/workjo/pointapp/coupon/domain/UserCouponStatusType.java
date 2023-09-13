package com.workjo.pointapp.coupon.domain;


import com.workjo.pointapp.common.AbstractBaseEnumConverter;
import com.workjo.pointapp.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum UserCouponStatusType implements BaseEnum<String, String> {
	AVAILABLE("A", "AVAILABLE"),
	USED("U", "USED"),
	EXPIRED("E", "EXPIRED");

	private final String code;
	private final String value;

	@jakarta.persistence.Converter(autoApply = true)
	static class Converter extends AbstractBaseEnumConverter<UserCouponStatusType, String, String> {

		public Converter() {
			super(UserCouponStatusType.class);
		}

	}
}
