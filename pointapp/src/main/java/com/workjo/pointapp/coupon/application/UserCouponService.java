package com.workjo.pointapp.coupon.application;


import java.util.UUID;


public interface UserCouponService {

	void userDownloadCoupon(Long couponId, UUID uuid);

}
