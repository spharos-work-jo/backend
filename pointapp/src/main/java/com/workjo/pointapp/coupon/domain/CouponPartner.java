package com.workjo.pointapp.coupon.domain;


import com.workjo.pointapp.common.domain.BaseDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coupon_partner")
public class CouponPartner extends BaseDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 45, name = "name")
	private String name;
	@Column(length = 255, name = "image_url")
	private String imageUrl;

}
