package com.workjo.pointapp.batchtask.presentation;


import com.workjo.pointapp.batchtask.repository.EventApplyRedisService;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.event.common.domain.Event;
import com.workjo.pointapp.event.common.infrastructure.IEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/batch-run")
public class BatchTestController {

	private final EventApplyRedisService eventApplyRedisService;
	private final IEventRepository eventRepository;
	private final JobLauncher jobLauncher;

	@Qualifier("myJob")
	private final Job job;


	@GetMapping("")
	@ResponseBody
	public ApiResponse<Void> handle() {

		LocalDateTime yesterdayStartDateTime = LocalDateTime.now().minusDays(1L).withHour(0).withMinute(0).withSecond(0).withNano(0);
		LocalDateTime todayStartDateTime = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
		//		if(yesterdayDateTime.getHour() != 0){   // 0시가 아니면
		//			yesterdayDateTime = yesterdayDateTime.plusDays(1L).withHour(0).withMinute(0).withSecond(0).withNano(0);
		//		}
		List<Event> eventList = eventRepository.findByDrawDateBetween(yesterdayStartDateTime, todayStartDateTime);
		if (eventList.isEmpty()) {
			throw new CustomException(ErrorCode.BAD_REQUEST);
		}
		List<Long> eventIdList = eventList.stream().map(Event::getId).toList();
		log.info("eventIdList : {}", eventIdList);

		eventIdList.forEach(eventId -> {
			JobParameters jobParameters = new JobParametersBuilder()
				.addLong("eventId", eventId)
				.toJobParameters();
			try {
				JobExecution jobExecution = jobLauncher.run(job, jobParameters);
				if (jobExecution.getExitStatus().equals(ExitStatus.COMPLETED)) {
					log.info("jobExecution.getExitStatus() : {}", jobExecution.getExitStatus());
					eventApplyRedisService.winningEventApply(eventId);
				} else {
					log.info("jobExecution.getExitStatus() : {}", jobExecution.getExitStatus());
					eventApplyRedisService.deleteEventApply(eventId);
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		});
		return ApiResponse.ofSuccess(null);
	}

}