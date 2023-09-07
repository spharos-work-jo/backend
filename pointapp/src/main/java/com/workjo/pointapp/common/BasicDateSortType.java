package com.workjo.pointapp.common;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.util.Objects;


/**
 * null 또는 잘못된 데이터 -> DEADLINE (마감 임박순)
 */
@Getter
public enum BasicDateSortType {
	RECENT,
	DEADLINE;


	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static BasicDateSortType findByCode(String code) {
		try {
			return BasicDateSortType.valueOf(code);
		} catch (IllegalArgumentException e) {
			return DEADLINE;
		}
	}


	/**
	 * Sort by column startDate or endDate
	 * recent: Sort by column startDate DESC
	 * deadline: Sort by column endDate ASC
	 *
	 * @param sortType 정렬 타입
	 */
	public static Sort getSortByColumnStartDateOrEndDate(BasicDateSortType sortType) {
		if (Objects.requireNonNull(sortType) == BasicDateSortType.RECENT) {
			return Sort.by(Sort.Direction.DESC, "startDate");
		}
		return Sort.by(Sort.Direction.ASC, "endDate");
	}

}
