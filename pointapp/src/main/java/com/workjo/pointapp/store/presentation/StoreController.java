package com.workjo.pointapp.store.presentation;


import com.workjo.pointapp.auth.AuthUtils;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.store.application.FavoriteStoreService;
import com.workjo.pointapp.store.application.StoreService;
import com.workjo.pointapp.store.dto.StoreGetDto;
import com.workjo.pointapp.store.vo.StoreGetOut;
import com.workjo.pointapp.store.vo.StoreMapGetOut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Tag(name = "Store Controller", description = "매장 조회, 단골 매장")
@RestController
@RequestMapping("/api/v1/store")
@Slf4j
@RequiredArgsConstructor
public class StoreController {

	private final ModelMapperBean modelMapperBean;

	private final StoreService storeService;
	private final FavoriteStoreService favoriteStoreService;


	@Operation(summary = "위도, 경도로 매장 조회", description = "위도, 경도로 매장 조회, 지도에서 사용")
	@GetMapping("/find-map")
	public ApiResponse<List<StoreMapGetOut>> getStoreListMap(
		@RequestParam("sw_lat") double bottomLat,
		@RequestParam("ne_lat") double topLat,
		@RequestParam("sw_lng") double leftLng,
		@RequestParam("ne_lng") double rightLng) {
		List<StoreGetDto> searchStoreList = storeService.getStoreListByLatLng(bottomLat, topLat, leftLng, rightLng);
		return ApiResponse.ofSuccess(searchStoreList.stream().map(o -> modelMapperBean.modelMapper().map(o, StoreMapGetOut.class)).toList());
	}


	@Operation(summary = "단골 매장 조회", description = "현재 로그인한 유저의 단골 매장 리스트 조회")
	@GetMapping("/fav")
	public ApiResponse<List<StoreGetOut>> getStoreListFavorite(Authentication authentication) {
		UUID uuid = AuthUtils.getCurrentUserUUID(authentication);
		List<StoreGetDto> favoriteStoreList = favoriteStoreService.getFavoriteStoreListByUserUUID(uuid);
		return ApiResponse.ofSuccess(favoriteStoreList.stream().map(o -> modelMapperBean.modelMapper().map(o, StoreGetOut.class)).toList());
	}


	@Operation(summary = "단골 매장 추가", description = "매장 아이디로 단골매장 추가")
	@PostMapping("/fav/{storeId}")
	public ApiResponse<Void> createFavoriteStore(@PathVariable Long storeId, Authentication authentication) {
		favoriteStoreService.createFavoriteStore(storeId, AuthUtils.getCurrentUserUUID(authentication));
		return ApiResponse.ofSuccess(null);
	}


	@Operation(summary = "단골 매장 삭제", description = "매장 아이디로 단골매장 삭제")
	@DeleteMapping("/fav/{storeId}")
	public ApiResponse<Void> deleteFavoriteStore(@PathVariable Long storeId, Authentication authentication) {
		favoriteStoreService.deleteFavoriteStore(storeId, AuthUtils.getCurrentUserUUID(authentication));
		return ApiResponse.ofSuccess(null);
	}

}
