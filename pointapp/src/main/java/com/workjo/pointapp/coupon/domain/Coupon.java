package com.workjo.pointapp.coupon.domain;


import com.workjo.pointapp.common.domain.BaseDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon extends BaseDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 45)
	private String name;
	@Column(length = 45)
	private String description;
	@Column
	private Integer couponValue;
	@Column
	private LocalDate startDate;
	@Column
	private LocalDate endDate;
	@Column
	private Integer downloadCount;
	@Column
	private Integer totalCount;
	@Column(nullable = false)
	private Integer serialNumber;
	@Column(length = 255)
	private String imageUrl;
	@Column
	private Long eventId;
	@Column(length = 3)
	private CouponType type;
	@Column(length = 500)
	private String guideline;

}
