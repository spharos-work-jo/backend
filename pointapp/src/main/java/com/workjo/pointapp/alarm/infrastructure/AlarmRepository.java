package com.workjo.pointapp.alarm.infrastructure;


import com.workjo.pointapp.alarm.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


public interface AlarmRepository extends JpaRepository<Alarm, Long> {

	List<Alarm> findAlarmByUserId(Long user);

	@Modifying
	@Transactional
	@Query("update Alarm a set a.isCheck = true where a.user.id = :userId and a.id = :alarmId")
	void updateAlarmCheckByUserIdAndAlarmId(Long userId, Long alarmId);
	@Modifying
	@Transactional
	@Query("update Alarm a set a.isCheck = true where a.user.id = :userId")
	void updateAlarmCheckByUserId(Long userId);

	@Transactional
	void deleteAllByRegDateBefore(LocalDateTime time);

}
