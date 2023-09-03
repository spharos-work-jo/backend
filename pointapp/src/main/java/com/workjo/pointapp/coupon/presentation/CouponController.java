package com.workjo.pointapp.coupon.presentation;


import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.coupon.application.CouponService;
import com.workjo.pointapp.coupon.dto.CouponGetDto;
import com.workjo.pointapp.coupon.vo.response.CouponGetRes;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "Coupon Controller", description = "쿠폰 API")
@RestController
@RequestMapping("/api/v1/coupon")
@Slf4j
@RequiredArgsConstructor
public class CouponController {

	private final ModelMapperBean modelMapperBean;
	private final CouponService couponService;


	@GetMapping("")
	public ApiResponse<List<CouponGetRes>> getCouponDownloadList() {
		List<CouponGetDto> couponGetDtoList = couponService.getCouponDownloadList();
		return ApiResponse.ofSuccess(couponGetDtoList.stream().map(o -> modelMapperBean.privateStrictModelMapper().map(o, CouponGetRes.class)).toList());
	}

}
