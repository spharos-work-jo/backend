package com.workjo.pointapp.coupon.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;


@Getter
public enum CouponSearchType {
	AVAILABLE,
	NON_AVAILABLE,  // 사용 불가능 (사용 완료 또는 기간 만료)
	USED,
	EXPIRED;


	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static CouponSearchType findByCode(String code) {
		try {
			return CouponSearchType.valueOf(code);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

}
