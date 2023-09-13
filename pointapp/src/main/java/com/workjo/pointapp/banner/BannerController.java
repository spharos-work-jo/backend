package com.workjo.pointapp.banner;


import com.workjo.pointapp.banner.dto.BannerGetDto;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.ModelMapperBean;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/banners")
public class BannerController {

	private final ModelMapperBean modelMapperBean;
	private final BannerService bannerService;

    @GetMapping
    public ApiResponse<List<Banner>> getBanners() {
        return ApiResponse.ofSuccess(bannerRepository.findAll());
    }

	@DeleteMapping("/{id}")
	public ApiResponse<Void> deleteBannerById(@PathVariable Long id) {
		bannerService.deleteBannerById(id);
		return ApiResponse.ofSuccess(null);
	}


}
