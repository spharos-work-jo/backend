package com.workjo.pointapp.alarm.infrastructure;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.workjo.pointapp.alarm.domain.QAlarm.alarm;


@Repository
@RequiredArgsConstructor
public class AlarmCustomRepositoryImpl implements AlarmCustomRepository {

	private final JPAQueryFactory queryFactory;


	@Transactional
	@Modifying(clearAutomatically = true)
	public void updateAlarmIsCheckByUserId(Long id) {
		queryFactory
			.update(alarm)
			.set(alarm.isCheck, true)
			.where(alarm.user.id.eq(id))
			.execute();
	}


	@Transactional
	@Modifying(clearAutomatically = true)
	@Override
	public void deleteOutOfDateAlarm(List<Long> idList) {
		queryFactory
			.delete(alarm)
			.where(alarm.id.in(idList))
			.execute();
	}

}
