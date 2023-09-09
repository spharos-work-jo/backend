package com.workjo.pointapp.coupon.presentation;


import com.workjo.pointapp.auth.AuthUtils;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.common.BasicDateSortType;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.coupon.application.CouponService;
import com.workjo.pointapp.coupon.application.UserCouponService;
import com.workjo.pointapp.coupon.domain.CouponSearchType;
import com.workjo.pointapp.coupon.dto.CouponFindDto;
import com.workjo.pointapp.coupon.dto.CouponGetDto;
import com.workjo.pointapp.coupon.dto.CouponIdSliceDto;
import com.workjo.pointapp.coupon.dto.CouponUserSearchDto;
import com.workjo.pointapp.coupon.vo.response.CouponGetRes;
import com.workjo.pointapp.coupon.vo.response.CouponIdSliceRes;
import io.swagger.v3.oas.annotations.Operation;
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
	private final UserCouponService userCouponService;


	@Operation(summary = "쿠폰 id 리스트(메인화면)", description = "쿠폰 id 리스트, first 여부, last 여부")
	@GetMapping("")
	public ApiResponse<CouponIdSliceDto> getCouponList(
		Pageable pageable,
		@RequestParam(value = "sortType", required = false) BasicDateSortType sortType) {
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), BasicDateSortType.getSortByColumnStartDateOrEndDate(sortType));
		CouponIdSliceDto couponIdSliceDto = couponService.getCouponIdList(pageable);
		return ApiResponse.ofSuccess(couponIdSliceDto);
	}


	@Operation(summary = "쿠폰 상세 조회", description = "로그인 했을 경우, 다운로드한 쿠폰 정보 함께 반환")
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


	@Operation(summary = "유저 다운로드한 쿠폰의 id 리스트", description = "유저 쿠폰에서 쿠폰 id 리스트 조회. 쿠폰 id 리스트, 검색 조건, first 여부, last 여부 return")
	@GetMapping("/my")
	public ApiResponse<CouponIdSliceRes> getCouponListFromUserCoupon(
		Pageable pageable,
		@RequestParam(value = "searchType", required = false) CouponSearchType searchType,
		@RequestParam(value = "sortType", required = false) BasicDateSortType sortType,
		Authentication authentication) {
		UUID uuid = AuthUtils.getCurrentUserUUID(authentication);
		CouponIdSliceDto couponIdSliceDto = couponService.getCouponIdListfromUserCouponAndCoupon(
			CouponUserSearchDto.builder()
				.pageable(pageable)
				.searchType(searchType)
				.basicDateSortType(sortType)
				.uuid(uuid)
				.build()
		);
		return ApiResponse.ofSuccess(modelMapperBean.privateStrictModelMapper().map(couponIdSliceDto, CouponIdSliceRes.class));
	}


	@Operation(summary = "쿠폰 다운로드", description = "유저 쿠폰 생성")
	@PostMapping("/my/{couponId}")
	public ApiResponse<Void> createUserCoupon(@PathVariable Long couponId, Authentication authentication) {
		UUID uuid = AuthUtils.getCurrentUserUUID(authentication);
		userCouponService.createUserCoupon(couponId, uuid);
		return ApiResponse.ofSuccess(null);
	}

}
