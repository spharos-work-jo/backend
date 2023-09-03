package com.workjo.pointapp.coupon.domain;


import com.workjo.pointapp.common.AbstractBaseEnumConverter;
import com.workjo.pointapp.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum CouponType implements BaseEnum<String, String> {
	DISCOUNT_FIXED("DF", "DISCOUNT_FIXED"),
	DISCOUNT_RATE("DR", "DISCOUNT_RATE"),
	EXCHANGE("EX", "EXCHANGE");
	private final String code;
	private final String value;

	@jakarta.persistence.Converter(autoApply = true)
	static class Converter extends AbstractBaseEnumConverter<CouponType, String, String> {

		public Converter() {
			super(CouponType.class);
		}

	}
}
