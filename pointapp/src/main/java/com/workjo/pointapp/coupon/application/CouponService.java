package com.workjo.pointapp.coupon.application;


import com.workjo.pointapp.coupon.dto.CouponFindDto;
import com.workjo.pointapp.coupon.dto.CouponGetDto;
import com.workjo.pointapp.coupon.dto.CouponIdSliceDto;
import com.workjo.pointapp.coupon.dto.CouponUserSearchDto;
import org.springframework.data.domain.Pageable;


public interface CouponService {

	/**
	 * 쿠폰의 id 리스트를 반환
	 */
	CouponIdSliceDto getCouponIdList(Pageable pageable);

	/**
	 * 쿠폰의 상세 정보를 반환
	 */
	CouponGetDto getCoupon(CouponFindDto couponFindDto);
	/**
	 * 유저 쿠폰에서 조회 조건에 따라 쿠폰의 아이디 리스트를 반환
	 */
	CouponIdSliceDto getCouponIdListfromUserCouponAndCoupon(CouponUserSearchDto build);

}
