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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
		String uuidString = authService.getCurrentUserUUID(authentication).toString();
		List<StoreGetDto> favoriteStoreList = favoriteStoreService.getFavoriteStoreListByUserUUIDString(uuidString);
		return ApiResponse.ofSuccess(favoriteStoreList.stream().map(o -> modelMapperBean.modelMapper().map(o, StoreGetOut.class)).toList());
	}

}
