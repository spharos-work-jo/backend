package com.workjo.pointapp.coupon.application;


import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.coupon.domain.Coupon;
import com.workjo.pointapp.coupon.dto.CouponGetDto;
import com.workjo.pointapp.coupon.infrastructure.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CouponServiceImpl implements CouponService {

	private final ModelMapperBean modelMapperBean;
	private final CouponRepository couponRepository;


	@Override
	public List<CouponGetDto> getCouponDownloadList() {
		List<Coupon> couponList = couponRepository.getByEndDateIsGreaterThanEqual(LocalDate.now());
		return couponList.stream().map(o -> modelMapperBean.modelMapper().map(o, CouponGetDto.class)).toList();
	}

}
