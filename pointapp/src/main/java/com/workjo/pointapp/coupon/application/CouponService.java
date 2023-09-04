package com.workjo.pointapp.coupon.application;


import com.workjo.pointapp.coupon.dto.CouponGetDto;
import com.workjo.pointapp.coupon.dto.CouponSortParamDto;

import java.util.List;


public interface CouponService {

	List<CouponGetDto> getCouponList(CouponSortParamDto couponSortParamDto);

}
