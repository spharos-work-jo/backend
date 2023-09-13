package com.workjo.pointapp.coupon.infrastructure;


import com.workjo.pointapp.coupon.dao.CouponIdDao;
import com.workjo.pointapp.coupon.domain.Coupon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Slice<CouponIdDao> getByStartDateIsLessThanEqualAndEndDateIsGreaterThanEqual(LocalDate now1, LocalDate now2, Pageable pageable);
	List<Coupon> findByEndDate(LocalDate time);

}
