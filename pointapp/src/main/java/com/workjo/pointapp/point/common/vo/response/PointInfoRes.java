package com.workjo.pointapp.point.common.vo.response;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@Builder
public class PointInfoRes {

	private Integer usableTotalPoint;
	@Builder.Default
	private Integer willGetPoint = 0;
	@Builder.Default
	private Integer expPoint1 = 0;
	@Builder.Default
	private Integer expPoint2 = 0;
	private LocalDate expDate1;
	private LocalDate expDate2;

}
