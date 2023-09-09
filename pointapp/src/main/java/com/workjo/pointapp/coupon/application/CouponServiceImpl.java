package com.workjo.pointapp.coupon.application;


import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.coupon.dao.CouponIdDao;
import com.workjo.pointapp.coupon.domain.Coupon;
import com.workjo.pointapp.coupon.dto.CouponFindDto;
import com.workjo.pointapp.coupon.dto.CouponGetDto;
import com.workjo.pointapp.coupon.dto.CouponIdSliceDto;
import com.workjo.pointapp.coupon.dto.CouponUserSearchDto;
import com.workjo.pointapp.coupon.infrastructure.CouponRepository;
import com.workjo.pointapp.coupon.infrastructure.UserCouponCustomRepository;
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


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CouponServiceImpl implements CouponService {

	private final ModelMapperBean modelMapperBean;
	private final CouponRepository couponRepository;
	private final UserCouponRepository userCouponRepository;
	private final UserCouponCustomRepository userCouponCustomRepository;
	private final UserRepository userRepository;


	@Override
	public CouponIdSliceDto getCouponIdList(Pageable pageable) {
		Slice<CouponIdDao> couponSlice = couponRepository.getByStartDateIsLessThanEqualAndEndDateIsGreaterThanEqual(LocalDate.now(), LocalDate.now(),
			pageable);
		return CouponIdSliceDto.fromCouponIdSlice(couponSlice);
	}


	@Override
	public CouponGetDto getCoupon(CouponFindDto couponFindDto) {
		log.info("couponFindDto : {}", couponFindDto);
		Coupon coupon = couponRepository.findById(couponFindDto.getId()).orElseThrow(() -> new CustomException(ErrorCode.BAD_REQUEST));
		log.info("coupon : {}", coupon.getId());
		CouponGetDto couponGetDto = modelMapperBean.privateStrictModelMapper().map(coupon, CouponGetDto.class);
		log.info("couponGetDto : {}", couponGetDto);
		couponGetDto.setPartnerDatafromPartner(coupon.getCouponPartner());
		if (couponFindDto.getUserUuid() != null) {
			User user = userRepository.findByUUID(couponFindDto.getUserUuid()).orElseThrow(() -> new CustomException(ErrorCode.DUPLICATE_RESOURCE));
			userCouponRepository.findByUserAndCoupon(user, coupon).ifPresent(couponGetDto::setUserCouponData);
		}
		couponGetDto.setRemainDayAndStatusByEndDate();
		return couponGetDto;
	}


	@Override
	public CouponIdSliceDto getCouponIdListfromUserCouponAndCoupon(CouponUserSearchDto searchDto) {
		User user = userRepository.findByUUID(searchDto.getUuid()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));
		Slice<Long> couponIdList = userCouponCustomRepository.findCouponIdListByUserIdFromUserCoupon(user.getId(), searchDto.getSearchType(), searchDto.getBasicDateSortType(),
			searchDto.getPageable());
		return CouponIdSliceDto.fromUserCouponSlice(couponIdList);
	}

}
