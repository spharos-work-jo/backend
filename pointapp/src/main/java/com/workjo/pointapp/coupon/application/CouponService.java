package com.workjo.pointapp.coupon.application;


import com.workjo.pointapp.common.domain.dto.SimpleSliceDto;
import com.workjo.pointapp.coupon.dto.CouponFindDto;
import com.workjo.pointapp.coupon.dto.CouponGetDto;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CouponService {

	/**
	 * 쿠폰의 id 리스트를 반환
	 */
	SimpleSliceDto<Long> getCouponIdList(Pageable pageable);

	/**
	 * 쿠폰의 상세 정보를 반환
	 */
	CouponGetDto getCoupon(CouponFindDto couponFindDto);

	/**
	 * 만료 날짜가 어제인 쿠폰의 id 리스트를 반환
	 */
	List<Long> findCouponIdEndDateIsYesterDay();

	void modifyCouponIsExpiredByCouponIdList(List<Long> idList);

}
