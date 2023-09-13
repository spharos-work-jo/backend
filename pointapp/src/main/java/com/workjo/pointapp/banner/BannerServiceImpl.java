package com.workjo.pointapp.banner;


import com.workjo.pointapp.banner.dto.BannerGetDto;
import com.workjo.pointapp.config.ModelMapperBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

	private final ModelMapperBean modelMapperBean;

	private final IBannerRepository bannerRepository;


	@Override
	public List<BannerGetDto> getBannerList() {
		List<Banner> bannerList = bannerRepository.findAllByOrderByOrderNumAsc();
		return bannerList.stream().map(banner -> modelMapperBean.privateStrictModelMapper().map(banner, BannerGetDto.class)).collect(java.util.stream.Collectors.toList());
	}


	@Override
	public void deleteBannerById(Long id) {
		bannerRepository.deleteById(id);
	}

}
