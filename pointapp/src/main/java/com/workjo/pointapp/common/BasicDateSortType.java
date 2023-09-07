package com.workjo.pointapp.common;


import lombok.Getter;
import org.springframework.data.domain.Sort;


/**
 * null 또는 잘못된 데이터 -> DEADLINE (마감 임박순)
 */
@Getter
public enum BasicDateSortType {
	RECENT,
	DEADLINE;


	/**
	 * Sort by column startDate or endDate
	 * recent: Sort by column startDate DESC
	 * deadline: Sort by column endDate ASC
	 *
	 * @param sortType 정렬 타입
	 */
	public static Sort getSortByColumnStartDateOrEndDate(BasicDateSortType sortType) {
		if (sortType == null || sortType == DEADLINE)
			return Sort.by(Sort.Direction.ASC, "endDate");
		return Sort.by(Sort.Direction.DESC, "startDate");
	}

}
