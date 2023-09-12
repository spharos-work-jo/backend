package com.workjo.pointapp.pointcard.presentation;


import com.workjo.pointapp.auth.AuthUtils;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.pointcard.application.PointCardService;
import com.workjo.pointapp.pointcard.dto.PointCardDto;
import com.workjo.pointapp.pointcard.vo.response.PointCardRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Point Card Controller", description = "유저의 point-card를 조회하는 API")
@RestController
@RequestMapping("/api/v1/point-card")
@Slf4j
@RequiredArgsConstructor
public class PointCardController {

	private final ModelMapperBean mapperBean;
	private final PointCardService pointCardService;


	@Operation(summary = "유저 통합 포인트카드 조회", description = "신세계 통합포인트 카드 조회")
	@GetMapping("")
	public ApiResponse<PointCardRes> getPointCard(Authentication authentication) {
		PointCardDto pointCardDto = pointCardService.getPointCardByUserUUID(AuthUtils.getCurrentUserUUID(authentication));
		PointCardRes pointCardRes = mapperBean.privateStrictModelMapper().map(pointCardDto, PointCardRes.class);
		return ApiResponse.ofSuccess(pointCardRes);
	}

}
