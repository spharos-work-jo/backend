package com.workjo.pointapp.coupon.infrastructure;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.workjo.pointapp.coupon.domain.QCoupon.coupon;


@Repository
@RequiredArgsConstructor
@Slf4j
public class CouponCustomRepositoryImpl implements CouponCustomRepository {

	private final JPAQueryFactory queryFactory;


	@Override
	@Modifying(clearAutomatically = true)
	public void updateCouponCount(Long couponId) {
		queryFactory.update(coupon)
			.set(coupon.downloadCount, coupon.downloadCount.add(1))
			.where(coupon.id.eq(couponId))
			.execute();
	}


	@Override
	@Modifying(clearAutomatically = true)
	public void updateCouponIsExpiredByCouponIdList(List<Long> couponIdList) {
		queryFactory
			.update(coupon)
			.set(coupon.isExpired, true)
			.where(coupon.id.in(couponIdList))
			.execute();
	}

}
