package com.workjo.pointapp.coupon.application;


import com.workjo.pointapp.common.domain.dto.SimpleSliceDto;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.coupon.dao.UserCouponSimpleDao;
import com.workjo.pointapp.coupon.domain.Coupon;
import com.workjo.pointapp.coupon.domain.UserCoupon;
import com.workjo.pointapp.coupon.dto.CouponUserSearchDto;
import com.workjo.pointapp.coupon.dto.UserCouponSimpleDto;
import com.workjo.pointapp.coupon.infrastructure.CouponCustomRepository;
import com.workjo.pointapp.coupon.infrastructure.CouponRepository;
import com.workjo.pointapp.coupon.infrastructure.UserCouponCustomRepository;
import com.workjo.pointapp.coupon.infrastructure.UserCouponRepository;
import com.workjo.pointapp.user.domain.User;
import com.workjo.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserCouponServiceImpl implements UserCouponService {

	private final ModelMapperBean modelMapperBean;
	private final CouponRepository couponRepository;
	private final UserCouponRepository userCouponRepository;
	private final UserRepository userRepository;
	private final UserCouponCustomRepository userCouponCustomRepository;
	private final CouponCustomRepository couponCustomRepository;


	@Override
	@Transactional
	public void createUserCoupon(Long couponId, UUID uuid) {
		// 쿠폰과 유저 정보를 가져옴
		Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));
		User user = userRepository.findByUUID(uuid).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));

		// 이미 다운로드한 쿠폰인지, 쿠폰 유효기간이 지났는지 확인
		if (userCouponRepository.existsByUserAndCoupon(user, coupon)) {
			throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
		} else if (coupon.getIsExpired()) {
			throw new CustomException(ErrorCode.BAD_REQUEST);
		}

		// 쿠폰 번호 생성 길이
		int couponLength = 12;

		// 쿠폰 번호 생성, 중복 방지
		String couponNum = makeRandomNum(coupon.getSerialNumber(), couponLength);
		while (userCouponRepository.existsByCouponAndCouponNum(coupon, couponNum)) {
			couponNum = makeRandomNum(coupon.getSerialNumber(), couponLength);
		}
		userCouponRepository.save(UserCoupon.builder()
			.coupon(coupon)
			.user(user)
			.couponNum(couponNum)
			.isUsed(false)
			.build());
		couponCustomRepository.updateCouponCount(couponId);
	}


	@Override
	public SimpleSliceDto<UserCouponSimpleDto> getUserCouponList(CouponUserSearchDto searchDto) {
		User user = userRepository.findByUUID(searchDto.getUuid()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));
		Slice<UserCouponSimpleDao> userCouponSimpleDaoSlice = userCouponCustomRepository.findIdListByUserIdFromUserCoupon(user.getId(), searchDto.getSearchType(), searchDto.getBasicDateSortType(),
			searchDto.getPageable());
		// dao -> dto로 변환
		Slice<UserCouponSimpleDto> userCouponSimpleDtoSlice = UserCouponSimpleDto.fromSimpleDaoSlice(userCouponSimpleDaoSlice);
		return SimpleSliceDto.fromSlice(userCouponSimpleDtoSlice);
	}


	private String makeRandomNum(int serialNumber, int length) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		while (sb.length() < length) {
			sb.append(r.nextInt(10));
		}
		return serialNumber + sb.toString();
	}

}
