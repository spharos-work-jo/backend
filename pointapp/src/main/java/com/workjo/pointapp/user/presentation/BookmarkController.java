package com.workjo.pointapp.user.presentation;


import com.workjo.pointapp.auth.AuthService;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.user.application.BookmarkService;
import com.workjo.pointapp.user.vo.BookmarkOut;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Bookmark Controller", description = "메뉴 즐겨찾기")
@RestController
@RequestMapping("/api/v1/bookmark")
@Slf4j
@RequiredArgsConstructor
public class BookmarkController {

	private final ModelMapperBean modelMapperBean;

	private final BookmarkService bookmarkService;
	private final AuthService authService;


	@GetMapping("")
	public ApiResponse<BookmarkOut> getBookmarkByLoginUser(Authentication authentication) {
		String uuid = authService.getCurrentUserUUID(authentication);
		log.debug(uuid);
		BookmarkOut bookmarkOut = modelMapperBean.modelMapper().map(bookmarkService.getUserBookmark(uuid), BookmarkOut.class);
		return ApiResponse.ofSuccess(bookmarkOut);
	}

}
