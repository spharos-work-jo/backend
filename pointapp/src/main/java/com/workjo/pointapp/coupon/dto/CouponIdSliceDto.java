package com.workjo.pointapp.coupon.dto;


import com.workjo.pointapp.coupon.dao.CouponIdDao;
import lombok.*;
import org.springframework.data.domain.Slice;

import java.util.List;


@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponIdSliceDto {

	List<Long> content;
	Boolean first;
	Boolean last;


	public static CouponIdSliceDto fromCouponIdSlice(Slice<CouponIdDao> couponIdSlice) {
		List<Long> content = couponIdSlice.getContent().stream().map(CouponIdDao::getId).toList();
		Boolean first = couponIdSlice.isFirst();
		Boolean last = couponIdSlice.isLast();

		return CouponIdSliceDto.builder()
			.content(content)
			.first(first)
			.last(last)
			.build();
	}


	public static CouponIdSliceDto fromUserCouponSlice(Slice<Long> userCouponIdSlice) {
		List<Long> content = userCouponIdSlice.getContent();
		Boolean first = userCouponIdSlice.isFirst();
		Boolean last = userCouponIdSlice.isLast();

		return CouponIdSliceDto.builder()
			.content(content)
			.first(first)
			.last(last)
			.build();
	}

}
