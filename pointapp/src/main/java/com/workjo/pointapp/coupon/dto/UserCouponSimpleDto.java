package com.workjo.pointapp.coupon.dto;


import com.workjo.pointapp.coupon.dao.UserCouponSimpleDao;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;


@Getter
@Builder
public class UserCouponSimpleDto {

	private Long couponId;
	private Long userCouponId;


	public static UserCouponSimpleDto fromEntity(UserCouponSimpleDao dao) {
		return UserCouponSimpleDto.builder()
			.couponId(dao.getCouponId())
			.userCouponId(dao.getUserCouponId())
			.build();
	}


	public static Slice<UserCouponSimpleDto> fromSimpleDaoSlice(Slice<UserCouponSimpleDao> userCouponSimpleDaoSlice) {
		return userCouponSimpleDaoSlice.map(UserCouponSimpleDto::fromEntity);
	}

}
