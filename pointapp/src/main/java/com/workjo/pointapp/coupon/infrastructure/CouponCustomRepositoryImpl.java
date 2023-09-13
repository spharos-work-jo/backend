package com.workjo.pointapp.coupon.infrastructure;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.workjo.pointapp.coupon.domain.QCoupon.coupon;


@Repository
@RequiredArgsConstructor
@Slf4j
public class CouponCustomRepositoryImpl implements CouponCustomRepository {

	private final JPAQueryFactory queryFactory;


	@Override
	public void updateCouponCount(Long couponId) {
		queryFactory.update(coupon)
			.set(coupon.downloadCount, coupon.downloadCount.add(1))
			.where(coupon.id.eq(couponId))
			.execute();
	}

}
