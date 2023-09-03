package com.workjo.pointapp.coupon.application;


import com.workjo.pointapp.coupon.dto.CouponGetDto;

import java.util.List;


public interface CouponService {

	List<CouponGetDto> getCouponDownloadList();

}
