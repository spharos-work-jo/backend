package com.workjo.pointapp.batchtask.repository;


import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.event.apply.infrastructure.EventApplyCustomRepository;
import com.workjo.pointapp.event.common.domain.reward.EventReward;
import com.workjo.pointapp.event.common.infrastructure.IEventRepository;
import com.workjo.pointapp.event.common.infrastructure.reward.IEventReward;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class EventApplyRedisService {

	private final static String EVENT_REDIS_KEY_PREFIX = "event:";

	private final RedisTemplate<String, String> redisTemplate;

	private final RedisCommands<String, String> redisCommands;

	private final IEventRepository eventRepository;
	private final IEventReward eventRewardRepository;
	private final EventApplyCustomRepository eventApplyCustomRepository;


	public Boolean saveEventApply(MultiValueMap<String, String> data) {
		List<Object> list = redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
			data.forEach((key, value) ->
				value.forEach(v ->
					connection.setCommands().sAdd(key.getBytes(), v.getBytes())));
			return null;
		});
		return !list.isEmpty();
	}


	@Transactional
	public void winningEventApply(Long eventId) {
		log.debug("winningEventApply eventId : {}", eventId);
		List<EventReward> eventRewardList = eventRewardRepository.findByEventIdOrderByRankingAsc(eventId);
		if (eventRewardList.isEmpty()) {
			throw new CustomException(ErrorCode.NOT_FOUND_RESOURCE);
		}

		int rewardCount = eventRewardList.stream().mapToInt(EventReward::getNumWinners).sum();
		String key = EVENT_REDIS_KEY_PREFIX + eventId;
		//		RedisCommands<String, String> redisCommands = new RedisConfig().redisCommands();
		List<Long> winApplyEventIdList = redisCommands.srandmember(key, rewardCount).stream()
			.map(Long::parseLong)
			.toList();

		// 이벤트 경품별 당첨자 수 만큼 반복
		//		for (EventReward reward : eventRewardList) {
		//			for (int i = 0; i < reward.getNumWinners(); i++) {
		//
		//			}
		//		}

		// 이벤트 당첨처리 간단하게 구현
		eventApplyCustomRepository.updateEventApplyStatusByIdList(winApplyEventIdList);
		eventApplyCustomRepository.updateEventApplyStatusByEventIdAndIdListNotIn(eventId, winApplyEventIdList);

		// 레디스 키 삭제
		redisCommands.del(key);
	}


	public void deleteEventApply(Long eventId) {
		String key = EVENT_REDIS_KEY_PREFIX + eventId;
		redisCommands.del(key);
	}

}
