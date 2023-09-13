package com.workjo.pointapp.coupon.application;


import com.workjo.pointapp.common.domain.dto.SimpleSliceDto;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.coupon.dao.CouponIdDao;
import com.workjo.pointapp.coupon.domain.Coupon;
import com.workjo.pointapp.coupon.dto.CouponFindDto;
import com.workjo.pointapp.coupon.dto.CouponGetDto;
import com.workjo.pointapp.coupon.infrastructure.CouponRepository;
import com.workjo.pointapp.coupon.infrastructure.UserCouponRepository;
import com.workjo.pointapp.user.domain.User;
import com.workjo.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CouponServiceImpl implements CouponService {

	private final ModelMapperBean modelMapperBean;
	private final CouponRepository couponRepository;
	private final UserCouponRepository userCouponRepository;

	private final UserRepository userRepository;


	@Override
	public SimpleSliceDto<Long> getCouponIdList(Pageable pageable) {
		// 시작 날짜가 오늘 이전이고, 종료 날짜가 오늘 이후인 쿠폰 id 리스트를 반환
		Slice<CouponIdDao> couponIdSlice = couponRepository.getByStartDateIsLessThanEqualAndEndDateIsGreaterThanEqual(LocalDate.now(), LocalDate.now(),
			pageable);
		List<Long> content = couponIdSlice.getContent().stream().map(CouponIdDao::getId).toList();
		return SimpleSliceDto.fromSlice(content, couponIdSlice);
	}


	@Override
	public CouponGetDto getCoupon(CouponFindDto couponFindDto) {
		Coupon coupon = couponRepository.findById(couponFindDto.getId()).orElseThrow(() -> new CustomException(ErrorCode.BAD_REQUEST));

		// 쿠폰 정보
		CouponGetDto couponGetDto = modelMapperBean.privateStrictModelMapper().map(coupon, CouponGetDto.class);
		couponGetDto.setPartnerDatafromCouponPartner(coupon.getCouponPartner());
		couponGetDto.setRemainDayByEndDate();
		if (couponFindDto.getUserUuid() != null) {  // 로그인 했을 경우 유저 쿠폰 정보 추가
			User user = userRepository.findByUUID(couponFindDto.getUserUuid()).orElseThrow(() -> new CustomException(ErrorCode.DUPLICATE_RESOURCE));
			userCouponRepository.findByUserAndCoupon(user, coupon).ifPresent(couponGetDto::setUserCouponData);
			couponGetDto.setUserCouponStatusByData();
		}
		return couponGetDto;
	}

}
