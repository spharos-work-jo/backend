package com.workjo.pointapp.coupon.dto;


import com.workjo.pointapp.common.BasicDateSortType;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponSortParamDto {

	private BasicDateSortType sortType;

}
