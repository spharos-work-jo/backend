package com.workjo.pointapp.coupon.infrastructure;


import com.workjo.pointapp.coupon.domain.Coupon;
import com.workjo.pointapp.coupon.domain.UserCoupon;
import com.workjo.pointapp.user.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {

	Optional<UserCoupon> findByUserAndCoupon(User user, Coupon coupon);
	Slice<UserCoupon> findByUser(User user, Pageable pageable);

}
