package com.workjo.pointapp.event.common.infrastructure.reward;

import com.workjo.pointapp.event.common.domain.reward.EventReward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventReward extends JpaRepository<EventReward, Long> {
}
