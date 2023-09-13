package com.workjo.pointapp.banner;


import com.workjo.pointapp.banner.dto.BannerGetDto;

import java.util.List;


public interface BannerService {

	List<BannerGetDto> getBannerList();
	void deleteBannerById(Long id);

}
