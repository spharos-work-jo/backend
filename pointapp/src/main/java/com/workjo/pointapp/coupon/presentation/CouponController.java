package com.workjo.pointapp.coupon.presentation;


import com.workjo.pointapp.auth.AuthUtils;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.common.BasicDateSortType;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.coupon.application.CouponService;
import com.workjo.pointapp.coupon.domain.CouponSearchType;
import com.workjo.pointapp.coupon.dto.CouponFindDto;
import com.workjo.pointapp.coupon.dto.CouponGetDto;
import com.workjo.pointapp.coupon.dto.CouponIdSliceDto;
import com.workjo.pointapp.coupon.dto.CouponUserSearchDto;
import com.workjo.pointapp.coupon.vo.response.CouponGetRes;
import com.workjo.pointapp.coupon.vo.response.CouponIdSliceRes;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Tag(name = "Coupon Controller", description = "쿠폰 API")
@RestController
@RequestMapping("/api/v1/coupon")
@Slf4j
@RequiredArgsConstructor
public class CouponController {

	private final ModelMapperBean modelMapperBean;
	private final CouponService couponService;


	@GetMapping("")
	public ApiResponse<CouponIdSliceDto> getCouponList(
		Pageable pageable,
		@RequestParam(value = "sortType", required = false) BasicDateSortType sortType) {
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), BasicDateSortType.getSortByColumnStartDateOrEndDate(sortType));
		CouponIdSliceDto couponIdSliceDto = couponService.getCouponList(pageable);
		return ApiResponse.ofSuccess(couponIdSliceDto);
	}


	@GetMapping("/{couponId}")
	public ApiResponse<CouponGetRes> getCoupon(@PathVariable Long couponId, Authentication authentication) {
		log.info("couponId : {}", couponId);
		UUID uuid;
		try {
			uuid = AuthUtils.getCurrentUserUUID(authentication);
		} catch (Exception e) {
			uuid = null;
		}
		CouponGetDto couponGetDto = couponService.getCoupon(CouponFindDto.builder()
			.id(couponId)
			.userUuid(uuid)
			.build());
		return ApiResponse.ofSuccess(modelMapperBean.privateStrictModelMapper().map(couponGetDto, CouponGetRes.class));
	}


	@GetMapping("/my")
	public ApiResponse<CouponIdSliceRes> getUserCouponList(
		@RequestParam Pageable pageable,
		@RequestParam(value = "searchType", required = false) CouponSearchType searchType,
		Authentication authentication) {

		UUID uuid = AuthUtils.getCurrentUserUUID(authentication);
		CouponIdSliceDto couponIdSliceDto = couponService.getUserDownloadCouponList(CouponUserSearchDto.builder().pageable(pageable).searchType(searchType).uuid(uuid).build());
		return ApiResponse.ofSuccess(modelMapperBean.privateStrictModelMapper().map(couponIdSliceDto, CouponIdSliceRes.class));
	}

	//	@PostMapping("/my/{couponId}")
	//	public ApiResponse<Void> downloadCoupon(@PathVariable Long couponId, Authentication authentication) {
	//		UUID uuid = AuthUtils.getCurrentUserUUID(authentication);
	//		couponService.userDownloadCoupon(couponId, uuid);
	//		return ApiResponse.ofSuccess(null);
	//	}

}
