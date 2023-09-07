package com.workjo.pointapp.coupon.domain;


import com.workjo.pointapp.common.domain.BaseDateTime;
import com.workjo.pointapp.user.domain.User;
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
@Table(name = "user_coupon")
public class UserCoupon extends BaseDateTime {

	@Column(columnDefinition = "tinyint default 0", name = "is_used")
	private Boolean isUsed;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "coupon_id")
	private Coupon coupon;

	@Column(length = 45, name = "coupon_num", updatable = false)
	private String couponNum;

}
