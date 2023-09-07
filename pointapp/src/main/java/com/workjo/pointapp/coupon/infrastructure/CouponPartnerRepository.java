package com.workjo.pointapp.coupon.infrastructure;


import com.workjo.pointapp.coupon.domain.CouponPartner;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CouponPartnerRepository extends JpaRepository<CouponPartner, Long> {
}
