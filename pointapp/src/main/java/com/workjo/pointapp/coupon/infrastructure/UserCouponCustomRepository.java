package com.workjo.pointapp.coupon.infrastructure;


import com.workjo.pointapp.common.BasicDateSortType;
import com.workjo.pointapp.coupon.dao.UserCouponSimpleDao;
import com.workjo.pointapp.coupon.domain.CouponSearchType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;


public interface UserCouponCustomRepository {

	/**
	 * 유저가 다운로드 한 쿠폰 id 리스트 조회
	 * 조회조건은 쿠폰 검색 타입, 정렬 타입, 페이징
	 */
	Slice<UserCouponSimpleDao> findIdListByUserIdFromUserCoupon(Long userId, CouponSearchType searchType, BasicDateSortType basicDateSortType, Pageable pageable);

}
