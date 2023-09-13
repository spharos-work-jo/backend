package com.workjo.pointapp.alarm.infrastructure;


import com.workjo.pointapp.alarm.domain.Alarm;
import com.workjo.pointapp.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


public interface AlarmRepository extends JpaRepository<Alarm, Long> {

	List<Alarm> findByUser(User user);

	@Transactional
	void deleteAllByRegDateBefore(LocalDateTime time);

	List<Alarm> findAllByRegDateBefore(LocalDateTime time);

}
