package com.workjo.pointapp.coupon.dto;


import com.workjo.pointapp.coupon.domain.CouponPartner;
import com.workjo.pointapp.coupon.domain.CouponType;
import com.workjo.pointapp.coupon.domain.UserCoupon;
import com.workjo.pointapp.coupon.domain.UserCouponStatusType;
import lombok.*;

import java.time.LocalDate;


@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
	private String couponNum;
	@Builder.Default
	private Boolean isDownloaded = false;
	private UserCouponStatusType userCouponStatus;
	private String partnerImageUrl;
	private String partnerThumbnailUrl;


	public void setUserCouponData(UserCoupon userCoupon) {
		if (userCoupon != null) {
			this.isDownloaded = true;
			this.couponNum = userCoupon.getCouponNum();
			this.userCouponStatus = userCoupon.getIsUsed() ? UserCouponStatusType.USED : UserCouponStatusType.AVAILABLE;
		}
	}


	public void setPartnerDatafromPartner(CouponPartner couponPartner) {
		this.partnerImageUrl = couponPartner.getImageUrl();
		this.partnerThumbnailUrl = couponPartner.getThumbnailUrl();
	}

}
