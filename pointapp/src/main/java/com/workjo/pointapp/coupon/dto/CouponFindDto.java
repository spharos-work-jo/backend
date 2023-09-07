package com.workjo.pointapp.coupon.dto;


import lombok.*;

import java.util.UUID;


@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponFindDto {

	private Long id;
	private UUID userUuid;

}
