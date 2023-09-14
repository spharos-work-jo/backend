package com.workjo.pointapp.batchtask.jobs;


import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.workjo.pointapp.batchtask.common.QuerydslPagingItemReader;
import com.workjo.pointapp.batchtask.repository.EventApplyRedisService;
import com.workjo.pointapp.batchtask.repository.EventBatchRepository;
import com.workjo.pointapp.event.apply.infrastructure.EventApplyCustomRepository;
import com.workjo.pointapp.event.common.infrastructure.IEventRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.workjo.pointapp.event.apply.domain.QEventApply.eventApply;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class TestJob {

	private final static int CHUNK_SIZE = 10;
	private final static String EVENT_REDIS_KEY_PREFIX = "event:";
	private final EntityManagerFactory entityManagerFactory;
	private final IEventRepository eventRepository;
	private final EventApplyRedisService eventApplyRedisService;

	private final PlatformTransactionManager transactionManager;
	private final JobRepository jobRepository;
	private final EventBatchRepository eventBatchRepository;
	private final JPAQueryFactory queryFactory;

	private final EventApplyCustomRepository eventApplyCustomRepository;


	@Bean
	public Job myJob() throws Exception {

		return new JobBuilder("myJob", jobRepository)
			.start(testStep(jobRepository, null))
			.build();
	}


	@Bean
	@JobScope
	public Step testStep(JobRepository jobRepository, @Value("#{jobParameters['eventId']}") Long eventId) throws Exception {
		return new StepBuilder("testStep", jobRepository)
			.<Tuple, MultiValueMap<String, String>>chunk(CHUNK_SIZE, transactionManager)
			.reader(reader(null))
			.processor(processor())
			.writer(writer())
			.build();
	}


	//
	//
	//	@Bean
	//	public Tasklet testTasklet2() {
	//		return ((contribution, chunkContext) -> {
	//			System.out.println("테스트2");
	//
	//			return RepeatStatus.FINISHED;
	//		});
	//	}
	@Bean
	@Transactional(readOnly = true)
	@StepScope
	public QuerydslPagingItemReader<Tuple> reader(@Value("#{jobParameters['eventId']}") Long eventId) {
		if (eventId != null) {
			log.info(eventId.toString());
			return new QuerydslPagingItemReader<>(entityManagerFactory, CHUNK_SIZE, queryFactory -> queryFactory
				.select(eventApply.id, eventApply.eventId)
				.from(eventApply)
				.where(eventApply.eventId.eq(eventId)));
		} else {
			log.info("eventIdList is empty");
			return null;
		}
	}


	@Bean
	public ItemProcessor<Tuple, MultiValueMap<String, String>> processor() {
		return tuple -> {
			String eventId = EVENT_REDIS_KEY_PREFIX + tuple.get(eventApply.eventId);
			String eventApplyId = tuple.get(eventApply.id).toString();
			MultiValueMap<String, String> eventApplyMap = new LinkedMultiValueMap<>();
			eventApplyMap.add(eventId, eventApplyId);
			return eventApplyMap;
		};
	}


	@Bean
	public ItemWriter<MultiValueMap<String, String>> writer() {
		return chunk -> {
			log.info("write 시작");
			// redisTemplete pipline을 이용해 set으로 저장
			for (MultiValueMap<String, String> stringStringMultiValueMap : chunk.getItems()) {
				eventApplyRedisService.saveEventApply(stringStringMultiValueMap);
			}
		};
	}

}