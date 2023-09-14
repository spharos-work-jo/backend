package com.workjo.pointapp.event.common.infrastructure.reward;


import com.workjo.pointapp.event.common.domain.reward.EventReward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IEventReward extends JpaRepository<EventReward, Long> {

	List<EventReward> findByEventIdOrderByRankingAsc(Long eventId);

}
