package com.workjo.pointapp.coupon.dto;


import com.workjo.pointapp.coupon.domain.CouponSearchType;
import lombok.*;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponUserSearchDto {

	private CouponSearchType searchType;
	private Pageable pageable;
	private UUID uuid;

}
