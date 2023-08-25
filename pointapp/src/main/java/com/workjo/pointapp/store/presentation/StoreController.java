package com.workjo.pointapp.store.presentation;


import com.workjo.pointapp.auth.AuthService;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.store.application.FavoriteStoreService;
import com.workjo.pointapp.store.application.StoreService;
import com.workjo.pointapp.store.dto.StoreGetDto;
import com.workjo.pointapp.store.vo.StoreGetOut;
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
	private final AuthService authService;

	private final StoreService storeService;
	private final FavoriteStoreService favoriteStoreService;


	@Operation(summary = "단골 매장 조회", description = "현재 로그인한 유저의 단골 매장 리스트 조회")
	@GetMapping("/fav")
	public ApiResponse<List<StoreGetOut>> getStoreListFavorite(Authentication authentication) {
		UUID uuid = authService.getCurrentUserUUID(authentication);
		List<StoreGetDto> favoriteStoreList = favoriteStoreService.getFavoriteStoreListByUserUUID(uuid);
		return ApiResponse.ofSuccess(favoriteStoreList.stream().map(o -> modelMapperBean.modelMapper().map(o, StoreGetOut.class)).toList());
	}


	@Operation(summary = "단골 매장 추가", description = "매장 아이디로 단골매장 추가")
	@PostMapping("/fav/{storeId}")
	public ApiResponse<Void> createFavoriteStore(@PathVariable Long storeId, Authentication authentication) {
		favoriteStoreService.createFavoriteStore(storeId, authService.getCurrentUserUUID(authentication));
		return ApiResponse.ofSuccess(null);
	}

}
