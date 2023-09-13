package com.workjo.pointapp.coupon.infrastructure;


import java.util.List;


public interface CouponCustomRepository {

	void updateCouponCount(Long couponId);

	void updateCouponIsExpiredByCouponIdList(List<Long> couponIdList);

}
