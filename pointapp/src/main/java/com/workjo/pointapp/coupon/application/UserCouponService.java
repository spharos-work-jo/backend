package com.workjo.pointapp.coupon.application;


import com.workjo.pointapp.common.domain.dto.SimpleSliceDto;
import com.workjo.pointapp.coupon.dto.CouponUserSearchDto;
import com.workjo.pointapp.coupon.dto.UserCouponSimpleDto;

import java.util.UUID;


public interface UserCouponService {

	void createUserCoupon(Long couponId, UUID uuid);
	/**
	 * 유저 쿠폰에서 조회 조건에 따라 쿠폰의 아이디 리스트를 반환
	 */
	SimpleSliceDto<UserCouponSimpleDto> getUserCouponList(CouponUserSearchDto searchDto);

}
