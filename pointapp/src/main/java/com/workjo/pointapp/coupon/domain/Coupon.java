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
@Table(name = "coupon")
public class Coupon extends BaseDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 45, name = "name")
	private String name;
	@Column(length = 45, name = "description")
	private String description;
	@Column(name = "coupon_value")
	private Integer couponValue;
	@Column(name = "start_date")
	private LocalDate startDate;
	@Column(name = "end_date")
	private LocalDate endDate;
	@Column(name = "download_count")
	private Integer downloadCount;
	@Column(name = "total_count")
	private Integer totalCount;
	@Column(nullable = false, name = "serial_number")
	private Integer serialNumber;
	@Column(length = 255, name = "image_url")
	private String imageUrl;
	@Column(name = "event_id")
	private Long eventId;
	@Column(length = 3, name = "type")
	private CouponType type;
	@Column(length = 500, name = "guideline")
	private String guideline;
	@ManyToOne
	@JoinColumn(name = "partner_id", nullable = false)
	private CouponPartner couponPartner;

}
