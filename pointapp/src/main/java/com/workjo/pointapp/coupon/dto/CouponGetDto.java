package com.workjo.pointapp.coupon.dto;


import com.workjo.pointapp.coupon.domain.CouponType;
import lombok.*;

import java.time.LocalDate;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponGetDto {

	private Long id;
	private String name;
	private String description;
	private Integer couponValue;
	private LocalDate startDate;
	private LocalDate endDate;
	private String imageUrl;
	private CouponType type;
	private String guideline;

}
