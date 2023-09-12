package com.workjo.pointapp.banner;

import com.workjo.pointapp.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/banners")
public class BannerController {
    private final IBannerRepository bannerRepository;


    @GetMapping
    public ApiResponse<List<Banner>> getBanners() {
        return ApiResponse.ofSuccess(bannerRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteBannerById(@PathVariable Long id) {
        bannerRepository.deleteById(id);

        return ApiResponse.ofSuccess(null);
    }


}
