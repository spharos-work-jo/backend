package com.workjo.pointapp.batchtask.reader;


import com.workjo.pointapp.batchtask.repository.EventBatchRepository;
import com.workjo.pointapp.event.apply.domain.EventApply;
import org.springframework.batch.item.database.AbstractPagingItemReader;

import java.util.ArrayList;
import java.util.List;


public class EventApplyRepositoryItemReader extends AbstractPagingItemReader<EventApply> {

	private final EventBatchRepository eventBatchRepository;
	private List<Long> eventIds = null;


	public EventApplyRepositoryItemReader(EventBatchRepository eventBatchRepository,
		List<Long> eventIds,
		int pageSize) {

		this.eventBatchRepository = eventBatchRepository;
		this.eventIds = eventIds;
		setPageSize(pageSize);
	}


	@Override // 직접 페이지 읽기 부분 구현
	protected void doReadPage() {
		if (results == null) {
			results = new ArrayList<>();
		} else {
			results.clear();
		}

		List<EventApply> products = eventBatchRepository.findPageByCreateDate(eventIds, getPageSize(), getPage());

		results.addAll(products);
	}

}