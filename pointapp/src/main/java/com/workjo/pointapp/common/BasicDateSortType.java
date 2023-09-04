package com.workjo.pointapp.common;


import lombok.Getter;
import org.springframework.data.domain.Sort;


@Getter
public enum BasicDateSortType {
	RECENT,
	DEADLINE;


	/**
	 * Sort by column startDate or endDate
	 * recent: Sort by column startDate DESC
	 * deadline: Sort by column endDate ASC
	 *
	 * @param sortType
	 */
	public static Sort getSortByColumnStartDateOrEndDate(BasicDateSortType sortType) {
		switch (sortType) {
		case RECENT:
			return Sort.by(Sort.Direction.DESC, "startDate");
		default:
			return Sort.by(Sort.Direction.ASC, "endDate");
		}
	}

}
