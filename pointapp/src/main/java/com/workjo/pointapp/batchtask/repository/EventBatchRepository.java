package com.workjo.pointapp.batchtask.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.workjo.pointapp.event.apply.domain.EventApply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.workjo.pointapp.event.apply.domain.QEventApply.eventApply;


@Repository
@RequiredArgsConstructor
public class EventBatchRepository {

	private final JPAQueryFactory queryFactory;


	public List<EventApply> findPageByCreateDate(List<Long> eventIdList, int pageSize, long offset) {
		return queryFactory
			.selectFrom(eventApply) // 실제 쿼리
			.where(eventApply.eventId.in(eventIdList)) // 실제 쿼리
			.limit(pageSize) // 페이징
			.offset(offset) // 페이징
			.fetch();
	}

}
