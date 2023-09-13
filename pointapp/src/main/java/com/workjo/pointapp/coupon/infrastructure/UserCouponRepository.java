package com.workjo.pointapp.coupon.infrastructure;


import com.workjo.pointapp.coupon.domain.Coupon;
import com.workjo.pointapp.coupon.domain.UserCoupon;
import com.workjo.pointapp.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {

	Optional<UserCoupon> findByIdAndUser(Long id, User user);
	Optional<UserCoupon> findByUserAndCoupon(User user, Coupon coupon);

	Boolean existsByUserAndCoupon(User user, Coupon coupon);

	Boolean existsByCouponAndCouponNum(Coupon coupon, String couponNum);

}
