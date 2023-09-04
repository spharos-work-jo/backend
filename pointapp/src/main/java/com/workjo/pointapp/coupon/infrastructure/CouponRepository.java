package com.workjo.pointapp.coupon.infrastructure;


import com.workjo.pointapp.coupon.domain.Coupon;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface CouponRepository extends JpaRepository<Coupon, Long> {

	List<Coupon> getByStartDateIsLessThanEqualAndEndDateIsGreaterThanEqual(LocalDate now1, LocalDate now2, Sort sort);

}
