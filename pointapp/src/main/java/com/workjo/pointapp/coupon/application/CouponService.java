package com.workjo.pointapp.coupon.application;


import com.workjo.pointapp.coupon.dto.CouponFindDto;
import com.workjo.pointapp.coupon.dto.CouponGetDto;
import com.workjo.pointapp.coupon.dto.CouponIdSliceDto;
import com.workjo.pointapp.coupon.dto.CouponUserSearchDto;
import org.springframework.data.domain.Pageable;


public interface CouponService {

	CouponIdSliceDto getCouponList(Pageable pageable);
	CouponGetDto getCoupon(CouponFindDto couponFindDto);
	CouponIdSliceDto getUserDownloadCouponList(CouponUserSearchDto build);

}
