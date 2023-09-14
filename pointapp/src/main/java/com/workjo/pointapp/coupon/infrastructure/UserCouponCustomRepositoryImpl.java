package com.workjo.pointapp.coupon.infrastructure;


import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.workjo.pointapp.common.BasicDateSortType;
import com.workjo.pointapp.common.CustomRepositorySliceUtil;
import com.workjo.pointapp.coupon.domain.CouponSearchType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static com.workjo.pointapp.coupon.domain.QCoupon.coupon;
import static com.workjo.pointapp.coupon.domain.QUserCoupon.userCoupon;


@Repository
@RequiredArgsConstructor
@Slf4j
public class UserCouponCustomRepositoryImpl implements UserCouponCustomRepository {

	private final JPAQueryFactory queryFactory;


	@Override
	public Slice<Long> findIdListByUserIdFromUserCoupon(Long userId, CouponSearchType searchType, BasicDateSortType basicDateSortType, Pageable pageable) {
		BooleanExpression expression = findByCouponSearchType(searchType);
		log.debug("expression : {}", expression);

		List<Long> couponIdList = queryFactory
			.select(userCoupon.coupon.id)
			.from(userCoupon)
			.leftJoin(userCoupon.coupon, coupon)
			.where(userCoupon.user.id.eq(userId))
			.where(expression)
			.orderBy(getCouponSpecifier(basicDateSortType))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1)
			.fetch();
		return CustomRepositorySliceUtil.toSlice(couponIdList, pageable);
	}


	private BooleanExpression isCouponExpired(Boolean isExpired) {
		return userCoupon.coupon.isExpired.eq(isExpired);
	}


	private BooleanExpression isUserCouponUsed(Boolean isUsed) {
		return userCoupon.isUsed.eq(isUsed);
	}


	/**
	 * 쿠폰 검색 타입에 따라 쿠폰을 조회하는 BooleanExpression 반환
	 */
	private BooleanExpression findByCouponSearchType(CouponSearchType searchType) {
		return switch (searchType) {
			case EXPIRED -> isCouponExpired(true).and(isUserCouponUsed(false));
			case USED -> isUserCouponUsed(true);
			case NON_AVAILABLE -> isCouponExpired(true).or(isUserCouponUsed(true));
			case AVAILABLE -> isCouponExpired(false).and(isUserCouponUsed(false));
			default -> throw new IllegalStateException("Unexpected value: " + searchType);
		};
	}


	/**
	 * basicDateSortType에 따라 coupon의 startDate 또는 endDate를 기준으로 정렬
	 */
	private OrderSpecifier<?> getCouponSpecifier(BasicDateSortType basicDataSortType) {
		if (Objects.isNull(basicDataSortType)) {    // 기본 정렬은 유저쿠폰 id 내림차순
			return new OrderSpecifier<>(Order.DESC, userCoupon.id);
		}
		return switch (basicDataSortType) {
			case RECENT -> new OrderSpecifier<>(Order.DESC, userCoupon.id);
			case DEADLINE -> new OrderSpecifier<>(Order.ASC, coupon.endDate);
		};
	}

}
