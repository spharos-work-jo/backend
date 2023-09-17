package com.workjo.pointapp.event.apply.infrastructure;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.workjo.pointapp.event.apply.domain.EventApplyStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.workjo.pointapp.event.apply.domain.QEventApply.eventApply;


@Repository
@RequiredArgsConstructor
public class EventApplyCustomRepository {

	private final JPAQueryFactory queryFactory;


	@Transactional
	@Modifying(clearAutomatically = true)
	public void updateEventApplyStatusByIdList(List<Long> idList) {
		queryFactory
			.update(eventApply)
			.set(eventApply.status, EventApplyStatus.WIN)
			.where(eventApply.id.in(idList))
			.execute();
	}


	@Transactional
	@Modifying(clearAutomatically = true)
	public void updateEventApplyStatusByEventIdAndIdListNotIn(Long eventId, List<Long> idList) {
		queryFactory
			.update(eventApply)
			.set(eventApply.status, EventApplyStatus.FAIL)
			.where(eventApply.eventId.eq(eventId), eventApply.id.notIn(idList))
			.execute();
	}

}
